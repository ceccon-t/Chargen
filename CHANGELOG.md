# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.3.0] - 2024-12-29

### Changed

- Grouped character infos form on main view
- Moved clear button to bottom of main view
- Refactored a lot of logic away from view class
- Added class to handle persistence
- Standardized terminology to use "avatar" instead of "image"

### Fixed

- Prevent bug when using accented letters for fields that are passed to Stable Diffusion model


## [1.2.0] - 2024-12-24

### Changed

- Generate biography with streaming text
- Block button for generating biography while generating
- Block button for generating avatar while generating
- Block generating biography if character infos have not been entirely filled
- Block generating avatar if character infos have not been entirely filled
- Block saving character if not generated neither biography nor avatar yet


## [1.1.0] - 2024-12-06

### Added

- JCommander library to parse command line options
- CLI option to configure LLM server port
- CLI option to configure Stable Diffusion server port
- CLI option to configure LLM model to be used when available
- CLI option to configure temperature used to generate text

### Fixed

- Log error when unable to generate biography


## [1.0.0] - 2024-11-29

### Added

- Initial release
- Form to enter character informations
- Generate character avatar image
- Generate character biography
- Save generated character
- Button to clear form
