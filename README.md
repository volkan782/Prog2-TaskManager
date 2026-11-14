# PTE Task Manager — Programming 2 OOP Project

**Contributor:** Volkan Gulsoy  
**Technology:** Java 17+, Maven, JUnit 5  
**Interface:** Console

## Problem and users
Students need a simple way to record homework tasks, prioritize urgent work, mark work complete, and keep their tasks after closing the program. PTE Task Manager is a local console-based task tracker.

## Prerequisites
- Java Development Kit (JDK) 17 or newer
- Apache Maven 3.8+

## Build, test, run
From the repository root:

```bash
mvn clean test
mvn package
mvn exec:java
```

The app saves tasks to `data/tasks.txt` when you choose **6 Save** or **0 Exit**. This local file is ignored by Git.

## Sample usage
```text
=== PTE Task Manager ===
1 Add task | 2 List priority | 3 List title | 4 Complete | 5 Delete | 6 Save | 0 Exit
Choose: 1
Type (REGULAR/URGENT): URGENT
Title: Finish Java project
Created #1 [OPEN] Finish Java project (URGENT, priority 10)
Choose: 2
#1 [OPEN] Finish Java project (URGENT, priority 10)
Choose: 4
Task ID: 1
Completed.
Choose: 0
Saved. Goodbye!
```

## OOP requirements and implementation
| Requirement | Evidence |
|---|---|
| Encapsulation | `Task` has private fields and validated constructor; controlled `complete()` |
| Abstraction | Abstract class `Task` |
| Inheritance | `RegularTask`, `UrgentTask` extend `Task` |
| Polymorphism | Overrides of `getPriority()` and `getType()` used through `Task` references |
| Relationships | `TaskManager` manages `List<Task>` |
| Generics and sorting | `ArrayList<Task>`, comparator lambdas for priority and title |
| Exceptions | `TaskException` for domain rules; input errors handled by console |
| Persistence | `TaskStorage` loads/saves UTF-8 text with try-with-resources |
| Design pattern | Factory: `TaskFactory.create()` selects concrete task types |
| Testing | JUnit 5 tests in `src/test/java` |

## Factory pattern
The **Factory** pattern solves the problem of choosing which subclass to instantiate from a user's task type. `TaskManager.add()` delegates creation to `TaskFactory.create()` rather than hard-coding subclass constructors. The participants are `TaskFactory`, abstract `Task`, `RegularTask`, and `UrgentTask`. They appear in `docs/uml.puml` and the source code.

## Design documentation
- [UML diagram source (PlantUML)](docs/uml.puml) — render with PlantUML or a compatible viewer
- [User stories](docs/user-stories.md)
- [Design decisions and limitations](docs/design.md)

## Evaluation
Run `mvn clean test`, then use the menu to create both task types, sort by priority, complete a task, exit, and restart. Confirm that saved tasks are restored.

## Known limitations
Single-user console program; no due dates or graphical interface. See `docs/design.md`.

## Release
Final intended version: `v1.0.0`. See `RELEASE_NOTES.md`. The Git tag and GitHub release must be created in the repository before submission.
