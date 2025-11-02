# DES Encryption & Decryption in Java

## Overview
This project is a complete Java implementation of the **Data Encryption Standard (DES)** algorithm. It allows users to encrypt and decrypt plaintext using an 8-character key, following all DES steps including key generation, initial and final permutations, S-Box substitutions, and block processing.

---

## Features
- Encrypts and decrypts plaintext of any length (pads with `#` if needed).  
- Generates 16-round DES keys for encryption and decryption.  
- Step-by-step output showing:  
  - Binary representation of plaintext and keys  
  - Initial and final permutations  
  - Expansion, XOR with key, S-Box substitution, and P-permutation  
  - Round-by-round transformations (L and R blocks)  
- Supports output in multiple formats:  
  - ASCII  
  - Hexadecimal  
  - Decimal  

---

## Usage
1. Compile and run the program:
```bash
javac DES.java
java DES
Enter the plaintext to encrypt.

Enter an 8-character key (mandatory).

The program displays:

Processed plaintext with padding

Round-by-round encryption process

Ciphertext (ASCII, Hex, Decimal)

Decryption process and final plaintext

Example
vbnet
Copy code
Enter plaintext: HELLODES
Enter key (must be 8 characters): MYSECRET

Original plaintext: "HELLODES" (8 chars)
Processed plaintext: "HELLODES" (8 chars)
Number of blocks: 1

ENCRYPTION KEY GENERATION
...

Ciphertext (ASCII): Òõ¥ßÍ¿Øé
Ciphertext (Hex): D2 F5 A5 DF CD BF D8 E9
Ciphertext (Decimal): 210 245 165 223 205 191 216 233

DECRYPTION PROCESS
...
Decrypted text: "HELLODES"
Notes
The key must be exactly 8 characters.

Plaintext is padded with # if its length is not a multiple of 8.

The program is intended for educational purposes, showing all internal DES steps clearly.

yaml
Copy code
