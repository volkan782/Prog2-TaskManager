
package hu.pte.taskmanager;

import hu.pte.taskmanager.service.*;
import hu.pte.taskmanager.model.*;
import hu.pte.taskmanager.exception.*;
import hu.pte.taskmanager.persistence.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    @Test
    void factoryUsesPolymorphism() {
        Task regular = TaskFactory.create("REGULAR", 1, "A", false);
        Task urgent = TaskFactory.create("URGENT", 2, "B", false);

        assertEquals(1, regular.getPriority());
        assertEquals(10, urgent.getPriority());
    }

    @Test
    void prioritySorting() {
        TaskManager m = new TaskManager();

        m.add("REGULAR", "A");
        m.add("URGENT", "B");

        assertEquals("B", m.listByPriority().get(0).getTitle());
    }

    @Test
    void domainRules() {
        TaskManager m = new TaskManager();

        assertThrows(
                TaskException.class,
                () -> m.add("REGULAR", " ")
        );

        assertThrows(
                TaskException.class,
                () -> m.complete(99)
        );

        m.add("REGULAR", "A");
        m.complete(1);

        assertThrows(
                TaskException.class,
                () -> m.complete(1)
        );
    }

    @Test
    void saveAndRestore(@TempDir Path temp) throws Exception {
        TaskManager m = new TaskManager();

        m.add("URGENT", "Study\tJava");
        m.complete(1);

        TaskStorage store = new TaskStorage(
                temp.resolve("tasks.txt")
        );

        store.save(m);

        TaskManager loaded = store.load();

        assertEquals(1, loaded.all().size());
        assertTrue(loaded.find(1).isCompleted());
        assertEquals(
                "Study\tJava",
                loaded.find(1).getTitle()
        );

        assertEquals(
                2,
                loaded.add("REGULAR", "Next").getId()
        );
    }

    @Test
    void corruptedFileFailsSafely(@TempDir Path temp) throws Exception {
        Path file = temp.resolve("tasks.txt");

        Files.writeString(file, "bad record");

        assertThrows(
                java.io.IOException.class,
                () -> new TaskStorage(file).load()
        );
    }

    @Test
    void filtersTasksByCompletionStatus() {
        TaskManager manager = new TaskManager();

        Task completed = manager.add("REGULAR", "Study Java");
        manager.add("URGENT", "Finish homework");

        manager.complete(completed.getId());

        assertEquals(
                1,
                manager.listByStatus(true).size()
        );

        assertEquals(
                1,
                manager.listByStatus(false).size()
        );

        assertEquals(
                "Study Java",
                manager.listByStatus(true).get(0).getTitle()
        );

        assertEquals(
                "Finish homework",
                manager.listByStatus(false).get(0).getTitle()
        );
    }
}
