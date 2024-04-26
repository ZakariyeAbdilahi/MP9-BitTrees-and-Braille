# Mini-Project: Bit trees and Braille

## Overview

This project explores the representation of binary information using Braille, a tactile writing system used by people with visual impairments. The main goal is to implement mechanisms for converting text to Braille and vice versa using binary trees to efficiently map Braille patterns to ASCII characters and Unicode symbols.


### Project Components

### `BrailleASCII.java`
**Purpose:** Serves as the main executable class, handling user input and output. It manages conversions between Braille and ASCII using command-line arguments.

### `BitTree.java`
**Purpose:** Implements a binary tree to store and retrieve mappings between Braille and ASCII efficiently. It supports dynamic construction and provides essential operations to interact with the tree structure.

### `BitTreeNode.java`
**Purpose:** Represents individual nodes within the `BitTree`. Each node can store a value and has pointers to its children, supporting the binary tree framework.

### `BrailleASCIITables.java`
**Purpose:** Stores and manages the mappings between ASCII characters and Braille patterns using instances of `BitTree`. It offers static methods for converting between these formats.

### `InvalidBitsException.java`
**Purpose:** A custom exception class that handles errors related to invalid bit patterns during conversion operations.

## Data Tables

- `ASCIIToBrailleTable.txt`: Lists mappings from ASCII characters to their corresponding Braille bit patterns.
- `BrailleToAsciiTable.txt`: Contains mappings from Braille bit patterns back to ASCII characters.
- `BrailleToUnicodeTable.txt`: Maps Braille bit patterns to Unicode Braille symbols for direct display.


## Authors

- **Samuel A. Rebelsky**
- **Zakariye Abdilahi**

## Acknowledgements

- **Samuel A. Rebelsky**: For providing project instructions and foundational code.