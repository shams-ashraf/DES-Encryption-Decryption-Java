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
javac DES.java
java DES
Enter the plaintext to encrypt.

Enter an 8-character key (mandatory).

The program displays:

Processed plaintext with padding

Round-by-round encryption process

Ciphertext (ASCII, Hex, Decimal)

Decryption process and final plaintext

---------------------------
Example
---------------------------

Enter plaintext: HELLODES
Enter key (must be 8 characters): MYSECRET

Original plaintext: "HELLODES" (8 chars)
Processed plaintext: "HELLODES" (8 chars)
Number of blocks: 1

---------------------------
ENCRYPTION KEY GENERATION
---------------------------
Key (binary): 010011010101100101010011010100110101010101011001...
After PC-1:  1101001010110010101010011010101001010101011001...
C0: 1101001010110010101010011010
D0: 1001010101011001010101011001

--- Round 1 --- Shift amount: 1 bit(s) to the left
C1 after shift: 1010010101100101010100110101
D1 after shift: 0010101010110010101010110011
Combined C+D: 10100101011001010101001101010010101010110010101010110011
Key (PC-2): 011010101011001010110010101011001010101011001010

--- Round 2 --- Shift amount: 1 bit(s) to the left
C2 after shift: ...
D2 after shift: ...
Combined C+D: ...
Key (PC-2): ...

... (Rounds 3–16 continue similarly)

---------------------------
ENCRYPTION PROCESS
---------------------------
ENCRYPTING BLOCK 1: "HELLODES"
Binary: 01001000010001010100110001001100010011110100110001000101...
After Initial Permutation (IP):
0011001010101010110101010101010110010101010101010101010101010101
L0: 00110010101010101101010101010101
R0: 10010101010101010101010101010101

ROUND 1
L1 = R0: 10010101010101010101010101010101
1. Expand R0 (32→48 bits):
100101010101010101010101010101010010101010101010
2. XOR with Round Key 1:
Key: 011010101011001010110010101011001010101011001010
Result: 111111111110011111100111111110011010101001100000
3. Apply S-Boxes (48→32 bits):
10101100101011001010110010101100
4. Apply Permutation P:
01011001010110100101100101011010
5. XOR with L0:
R1: 01101011111100001000110000001111

ROUND 2
L2 = R1: 01101011111100001000110000001111
1. Expand R1 (32→48 bits):
...
2. XOR with Round Key 2:
...
3. Apply S-Boxes:
...
4. Apply Permutation P:
...
5. XOR with L1:
R2: ...

... (Rounds 3–16 continue similarly)

After 32-bit swap (R16 + L16):
1100110011001100110011001100110011001100110011001100110011001100
After Final Permutation (FP):
0101010101010101010101010101010101010101010101010101010101010101

Ciphertext (ASCII): Òõ¥ßÍ¿Øé
Ciphertext (Hex): D2 F5 A5 DF CD BF D8 E9
Ciphertext (Decimal): 210 245 165 223 205 191 216 233

---------------------------
DECRYPTION PROCESS
---------------------------
DECRYPTING BLOCK 1: "Òõ¥ßÍ¿Øé"
Binary: 0101000101010101010101010101010101010101010101010101010101010101
After Initial Permutation (IP):
1001010101010101010101010101010110101010101010101010101010101010
L0: 10010101010101010101010101010101
R0: 10101010101010101010101010101010

ROUND 1
L1 = R0: 10101010101010101010101010101010
1. Expand R0 (32→48 bits):
...
2. XOR with Decryption Key 1:
...
3. Apply S-Boxes:
...
4. Apply Permutation P:
...
5. XOR with L0:
R1: ...

... (Rounds 2–16 continue similarly)

After 32-bit swap (R16 + L16):
01001000010001010100110001001100010011110100110001000101
After Final Permutation (FP):
01001000010001010100110001001100010011110100110001000101

Decrypted text: "HELLODES"

