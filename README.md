# Chargen

![Build status](https://github.com/ceccon-t/Chargen/actions/workflows/main-workflow.yml/badge.svg "Build status")

## Description

Desktop application to generate character avatars and biographies.

![Avatar and biography filled for Human Male Warrior character Gilew Aegold](https://raw.githubusercontent.com/ceccon-t/Chargen/main/images/Chargen_v1-0-0_sc0.png "Avatar and biography filled for Human Male Warrior character Gilew Aegold")

## How to use

Type in the information about your character on the top left form. After that, click on "Generate image" button to generate an avatar, and "Generate bio" to generate a biography.

If you like the result, click on the "Save character" button at the bottom to save the generated content to disk. A folder will be created with the date and name of the character, containing a JPEG image for the avatar and a plain text file for the biography with all the information of the character.

![Avatar and biography filled for Elf Female Sorcerer character Finduilye Nimrielye](https://raw.githubusercontent.com/ceccon-t/Chargen/main/images/Chargen_v1-0-0_sc1.png "Avatar and biography filled for Elf Female Sorcerer character Finduilye Nimrielye")


## Dependencies

The application assumes you have an OpenAI-compatible LLM server listening on port 8080 of your computer and a Stable Diffusion image generation model server listening on port 7860. If you have experience configuring and running them, feel free to use your favorite ones - if you do not have experience, the easiest way to get the LLM part running is to use [llamafile](https://github.com/mozilla-Ocho/llamafile), while for the Stable Diffusion model the classic solution is [text-generation-webui](https://github.com/oobabooga/text-generation-webui). Both of these will already start the servers listening on the expected ports by default.

Don't like the results you have been getting? Just try different models!

## How to run

The application is written in Java, so you will need to have the Java runtime installed. Assuming it has already been installed, either download the Jar file from the latest entry in the [Releases](https://github.com/ceccon-t/Chargen/releases) section of this repository or build the project following the instructions below, and execute it.

Command to run:

`$ java -jar Chargen.jar`

If building the project with Maven, instead of `Chargen.jar` be sure to use the path to the generated jar, which will be in the `target` directory and have the version as a suffix.

## How to build the project

This is a simple Maven project, so the easiest way to build it is running `mvn clean package` in the Chargen folder (assuming Maven is installed - if not, check its site and install from there). A jar file containing everything the application needs to run will be created at `Chargen/target/Chargen-<VERSION>.jar`.

## More info

To get a short intro to how the code is organized, you can check `architecture.md`.

