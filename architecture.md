# Chargen - Architecture

This file describes the overall architecture of the project, including its most important design decisions, code structure and frameworks used.

## Design decisions

An important early decision (or "axiom") for this project was to try to avoid as much as possible frameworks that offered an API that is too abstract. Instead, the idea was to focus on building by hand most of the relevant logic, as the project itself was an exercise in getting familiar with interacting with LLM and Stable Diffusion models.

Another axiom is to work only with local models, both for text generation and image generation. While frontier models would likely generate better results, the purpose of the project is learning and exploring what can be achieved with models that can be owned by the users.

## Structure

### Project meta files

The main folder of the project is named `Chargen`, and it is present at the root of the repository. Also at the root, `README.md` gives a short presentation at a project level, while this `architecture.md` file should quickly put any developer up to speed as to how the code is laid out. The `images` folder at root level contains images used only in the presentation of the repository, not to be used inside the application.

### Code overview

The main class of the application is also the only one at the root package, `Chargen`.

The big picture view is that there are four main responsibilities in the project: allowing users to enter information about a character, generating an avatar image for this character, generating a textual biography for this character and handling the persistance of avatar and biography to disk.

The classes that interact with the APIs of the text generation model and of the image generation model are separated, with the prefix "LLM" for the first and "SD" for the second. The application is designed to work with any LLM that offers an OpenAI-compatible API and the Stable Diffusion family of image generation models (hence the "SD").

The code was initally written with a lot of the responsibilities, most poignantly the preparation of the prompt for the image generation model, inside the MainView UI class. The plan is to iteratively fix this by refactoring the code to a better design.

## CI/CD

The project uses Github Actions to automatically generate a new release whenever new changes that alter the application itself are pushed into the `main` branch.

If the build breaks, a red failure sign is displayed near the hash of the commit in the repository. If all goes well, a green success sign is displayed instead. A badge with the status of the latest build for the main branch is also displayed in the Readme of the project.

The script that defines the main workflow can be found under `.github/workflows/main-workflow.yml`.


## Libraries and Frameworks

[JCommander](https://jcommander.org/) for parsing command line options.

[Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html) as a build automation tool.

[JUnit](https://junit.org/junit5/docs/current/user-guide/) and [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html) for automated tests.

[Swing](https://docs.oracle.com/javase/tutorial/uiswing/) as the GUI framework.

[Github Actions](https://docs.github.com/en/actions/learn-github-actions) for continuous integration.

