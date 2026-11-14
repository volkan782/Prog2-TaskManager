# Design decisions
- Task is an abstract parent with private state and domain methods; RegularTask and UrgentTask override priority and type.
- TaskFactory is the **Factory** design pattern: the service does not need to know concrete constructors when creating tasks.
- TaskManager owns the task collection and rules; ConsoleUI handles interaction; TaskStorage handles disk I/O.
- Data is a UTF-8 text file with Base64-encoded titles. This avoids extra runtime dependencies and preserves tabs and non-ASCII text.
- IDs are unique positive integers, and the next ID is reconstructed after loading.

# Limitations
- Single-user local application; no networking or multi-user synchronization.
- Tasks have two fixed priority levels, not custom deadlines.
- Saving replaces the local file; it is not a transactional database.
- No GUI; console is the intended interface.
