# GitHub submission steps
1. Create a GitHub repository and grant the instructor access **before beginning repository development**, as the assignment requires. If starting now, do not claim this happened earlier.
2. Create issues for domain model, console interface, persistence, tests, and UML documentation.
3. Commit the project on `main` with a meaningful message. For future improvements, create a feature branch and open a pull request to `main`.
4. Run `mvn clean test` and `mvn exec:java` from a clean checkout.
5. Render `docs/uml.puml` to `docs/uml.svg` with PlantUML, and commit both files.
6. Create and push a final tag: `git tag v1.0.0` then `git push origin v1.0.0`.
7. Create a GitHub Release for `v1.0.0` using `RELEASE_NOTES.md`.
8. Submit the repository URL to the instructor. Confirm the instructor can access it.

Do not fabricate issue, branch, commit, or PR history. Create real records as you work.
