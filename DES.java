import java.util.Scanner;

public class DES {
    
    private static final int[] IP = {
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };
    
    private static final int[] FP = {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25
    };
    
    private static final int[] E = {
        32, 1, 2, 3, 4, 5,
        4, 5, 6, 7, 8, 9,
        8, 9, 10, 11, 12, 13,
        12, 13, 14, 15, 16, 17,
        16, 17, 18, 19, 20, 21,
        20, 21, 22, 23, 24, 25,
        24, 25, 26, 27, 28, 29,
        28, 29, 30, 31, 32, 1
    };
    
    private static final int[] P = {
        16, 7, 20, 21, 29, 12, 28, 17,
        1, 15, 23, 26, 5, 18, 31, 10,
        2, 8, 24, 14, 32, 27, 3, 9,
        19, 13, 30, 6, 22, 11, 4, 25
    };
    
    private static final int[] PC1 = {
        57, 49, 41, 33, 25, 17, 9,
        1, 58, 50, 42, 34, 26, 18,
        10, 2, 59, 51, 43, 35, 27,
        19, 11, 3, 60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
        7, 62, 54, 46, 38, 30, 22,
        14, 6, 61, 53, 45, 37, 29,
        21, 13, 5, 28, 20, 12, 4
    };
    
    private static final int[] PC2 = {
        14, 17, 11, 24, 1, 5,
        3, 28, 15, 6, 21, 10,
        23, 19, 12, 4, 26, 8,
        16, 7, 27, 20, 13, 2,
        41, 52, 31, 37, 47, 55,
        30, 40, 51, 45, 33, 48,
        44, 49, 39, 56, 34, 53,
        46, 42, 50, 36, 29, 32
    };
    
    private static final int[] SHIFTS = {
        1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    };
    
    private static final int[][][] SBOX = {
        {
            {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
        },
        {
            {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
            {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
            {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
            {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
        },
        {
            {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
            {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
            {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
            {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
        },
        {
            {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
            {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
            {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
            {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
        },
        {
            {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
            {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
            {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
            {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
        },
        {
            {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
            {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
            {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
            {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
        },
        {
            {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
            {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
            {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
            {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
        },
        {
            {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
            {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
            {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
            {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
        }
    };
    
    private static String[] encryptionKeys = new String[16];
    private static String[] decryptionKeys = new String[16];
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("\nEnter plaintext: ");
        String plaintext = scanner.nextLine();
        String key;
        while (true) {
            System.out.print("Enter key (must be 8 characters): ");
            key = scanner.nextLine();
            if (key.length() == 8) {
                break;
            } else {
                System.out.println("Error: Key must be exactly 8 characters! Please try again.");
            }
        }
         
        String processedPlaintext = handlePlaintext(plaintext);
        
        System.out.println("Original plaintext: \"" + plaintext + "\" (" + plaintext.length() + " chars)");
        System.out.println("Processed plaintext: \"" + processedPlaintext + "\" (" + processedPlaintext.length() + " chars)");
        System.out.println("Number of blocks: " + processedPlaintext.length() / 8);

        System.out.println(" \n           ENCRYPTION     ");
        System.out.println("\nENCRYPTION KEY GENERATION\n");
        generateEncryptionKeys(key);
        
        System.out.println("\n         ENCRYPTION PROCESS  ");
        String ciphertext = encrypt(processedPlaintext);
        
        System.out.println("   \n       FINAL RESULTS  ");
        System.out.println("\nCiphertext (ASCII): " + ciphertext);
        System.out.println("Ciphertext (Hex):   " + stringToHex(ciphertext));
        System.out.println("Ciphertext (Decimal): " + stringToDecimal(ciphertext));
        
         System.out.println("     \n       DECRYPTION   ");
        System.out.println("\nDECRYPTION KEY GENERATION\n");
        generateDecryptionKeys(key);
        System.out.println("\n       DECRYPTION PROCESS");
        String decrypted = decrypt(ciphertext);
        
        System.out.println("                DECRYPTION RESULT");
        System.out.println("Decrypted text: \"" + decrypted + "\"");
        
        scanner.close();
    }
    
    private static String handlePlaintext(String text) {
        int len = text.length();
        
        if (len < 8) {
            while (text.length() < 8) {
                text += "#";
            }
        } else if (len > 8) {
            int remainder = len % 8;
            if (remainder != 0) {
                int padding = 8 - remainder;
                for (int i = 0; i < padding; i++) {
                    text += "#";
                }
            }
        }
        
        return text;
    }
    
    private static void generateEncryptionKeys(String key) {
        String binaryKey = stringToBinary(key);
        System.out.println("Key (binary): " + binaryKey);
        
        String permutedKey = permute(binaryKey, PC1);
        System.out.println("After PC-1:   " + permutedKey);
        
        String C = permutedKey.substring(0, 28);
        String D = permutedKey.substring(28, 56);
        System.out.println("C (left 28):  " + C);
        System.out.println("D (right 28): " + D);
        
        for (int i = 0; i < 16; i++) {
            System.out.println("\n--- Round " + (i+1) + " --- Shift amount: " + SHIFTS[i] + " bit(s) to the left");
            
            C = leftShift(C, SHIFTS[i]);
            D = leftShift(D, SHIFTS[i]);
            
            System.out.println("C after shift: " + C);
            System.out.println("D after shift: " + D);
            
            String combined = C + D;
            System.out.println("Combined C+D: " + combined);
            
            encryptionKeys[i] = permute(combined, PC2);
            System.out.println("Key (PC-2):  " + encryptionKeys[i]);
        }
    }
    
   private static void generateDecryptionKeys(String key) {
    String binaryKey = stringToBinary(key);
    System.out.println("Key (binary): " + binaryKey);

    String permutedKey = permute(binaryKey, PC1);
    System.out.println("After PC-1:  " + permutedKey);

    String C = permutedKey.substring(0, 28);
    String D = permutedKey.substring(28, 56);
    System.out.println("C0: " + C);
    System.out.println("D0: " + D);

    for (int i = 0; i < 16; i++) {
        int shiftAmount;
        if (i == 0) {             
            shiftAmount = 0;
        } else if (i == 1 || i == 8 || i == 15) { 
            shiftAmount = 1;
        } else {                 
            shiftAmount = 2;
        }

        System.out.println("\n--- Round " + (i+1) + " --- Right shift by " + shiftAmount);
        if (shiftAmount != 0) {
            C = rightShift(C, shiftAmount);
            D = rightShift(D, shiftAmount);
        }

        String combined = C + D;
        decryptionKeys[i] = permute(combined, PC2);

        System.out.println("C: " + C);
        System.out.println("D: " + D);
        System.out.println("Combined C+D: " + combined);
        System.out.println("Decryption Key (PC-2): " + decryptionKeys[i]);
    }
}

    private static String encrypt(String plaintext) {
        String result = "";
        
        for (int block = 0; block < plaintext.length() / 8; block++) {
            String blockText = plaintext.substring(block * 8, (block + 1) * 8);
            System.out.println("\nENCRYPTING BLOCK " + (block + 1) + ": \"" + blockText + "\"");
            
            String binaryBlock = stringToBinary(blockText);
            System.out.println("Binary: " + binaryBlock);
            
            String permuted = permute(binaryBlock, IP);
            System.out.println("After Initial Permutation (IP):");
            System.out.println(permuted);
            
            String left = permuted.substring(0, 32);
            String right = permuted.substring(32, 64);
            System.out.println("L0: " + left);
            System.out.println("R0: " + right);
            
            for (int i = 0; i < 16; i++) {
                System.out.println("\n   ROUND " + (i+1));
                
                String prevLeft = left;
                left = right;
                System.out.println("\nL" + (i+1) + " = R" + i + ": " + left);
                
                String expanded = permute(right, E);
                System.out.println("1. Expand R" + i + " (32 bits → 48 bits):");
                System.out.println("   " + expanded);
                
                System.out.println("2. XOR with Round Key " + (i+1) + ":");
                System.out.println("  Key:    " + encryptionKeys[i]);
                String xored = xor(expanded, encryptionKeys[i]);
                System.out.println("  Result: " + xored);
                
                System.out.println("3. Apply S-Boxes (48 bits → 32 bits):");
                String sboxOutput = applySBoxes(xored);
                System.out.println("   " + sboxOutput);
                
                System.out.println("4. Apply Permutation P:");
                String permutedOutput = permute(sboxOutput, P);
                System.out.println("   " + permutedOutput);
                
                System.out.println("5. XOR with L" + i + ":");
                System.out.println("   L" + i + ":     " + prevLeft);
                System.out.println("   P output: " + permutedOutput);
                right = xor(prevLeft, permutedOutput);
                System.out.println("   R" + (i+1) + ":     " + right);
            }
            
            String combined = right + left;
            System.out.println("After 32-bit swap (R16 + L16):");
            System.out.println(combined);
            
            String cipherBinary = permute(combined, FP);
            System.out.println("\nAfter Final Permutation (FP):");
            System.out.println(cipherBinary);
            
            result += binaryToString(cipherBinary);
        }
        
        return result;
    }
    
    private static String decrypt(String ciphertext) {
        StringBuilder result = new StringBuilder();
    
        for (int block = 0; block < ciphertext.length() / 8; block++) {
            String blockText = ciphertext.substring(block * 8, (block + 1) * 8);
            System.out.println("\nDECRYPTING BLOCK " + (block + 1) + ": \"" + blockText + "\"");
    
            String binaryBlock = stringToBinary(blockText);
            System.out.println("Binary: " + binaryBlock);
    
            String permuted = permute(binaryBlock, IP);
            System.out.println("After Initial Permutation (IP):");
            System.out.println(permuted);
    
            String left = permuted.substring(0, 32);
            String right = permuted.substring(32, 64);
            System.out.println("L0: " + left);
            System.out.println("R0: " + right);
    
            for (int i = 0; i < 16; i++) {
                System.out.println("\nROUND " + (i+1));
                
                String prevLeft = left;
                left = right;
                System.out.println("L" + (i+1) + " = R" + i + ": " + left);
    
                String expanded = permute(right, E);
                System.out.println("1. Expand R" + i + " (32 bits → 48 bits):");
                System.out.println("   " + expanded);
                
                System.out.println("2. XOR with Decryption Key " + (i+1) + ":");
                System.out.println("   Key:    " + decryptionKeys[i]);
                String xored = xor(expanded, decryptionKeys[i]);
                System.out.println("   Result: " + xored);
                
                System.out.println("3. Apply S-Boxes (48 bits → 32 bits):");
                String sboxOutput = applySBoxes(xored);
                System.out.println("   " + sboxOutput);
                
                System.out.println("4. Apply Permutation P:");
                String permutedOutput = permute(sboxOutput, P);
                System.out.println("   " + permutedOutput);
                
                System.out.println("5. XOR with L" + i + ":");
                System.out.println("   L" + i + ":     " + prevLeft);
                System.out.println("   P output: " + permutedOutput);
                right = xor(prevLeft, permutedOutput);
                System.out.println("   R" + (i+1) + ":     " + right);
            }
    
            String combined = right + left;
            System.out.println("After 32-bit swap (R16 + L16):");
            System.out.println(combined);
    
            String plainBinary = permute(combined, FP);
            System.out.println("\nAfter Final Permutation (FP):");
            System.out.println(plainBinary);
            
            String decryptedBlock = binaryToString(plainBinary);
            System.out.println("\nDecrypted text: \"" + decryptedBlock + "\"");

            result.append(decryptedBlock);
        }
    
        String finalPlaintext = result.toString().replaceAll("#+$", "");
        return finalPlaintext;
    }

    private static String permute(String input, int[] table) {
        StringBuilder output = new StringBuilder();
        for (int i : table) {
            output.append(input.charAt(i - 1));
        }
        return output.toString();
    }
    
    private static String leftShift(String input, int shifts) {
        return input.substring(shifts) + input.substring(0, shifts);
    }
    
    private static String rightShift(String input, int shifts) {
        int len = input.length();
        return input.substring(len - shifts) + input.substring(0, len - shifts);
    }
    
    private static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }
    
    private static String applySBoxes(String input) {
        StringBuilder output = new StringBuilder();
        
        for (int i = 0; i < 8; i++) {
            String block = input.substring(i * 6, (i + 1) * 6);
            
            int row = Integer.parseInt("" + block.charAt(0) + block.charAt(5), 2);
            int col = Integer.parseInt(block.substring(1, 5), 2);
            
            int value = SBOX[i][row][col];
            String binary = String.format("%4s", Integer.toBinaryString(value)).replace(' ', '0');
            output.append(binary);
        }
        
        return output.toString();
    }
    
    private static String stringToBinary(String input) {
        StringBuilder binary = new StringBuilder();
        for (char c : input.toCharArray()) {
            binary.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }
        return binary.toString();
    }
    
    private static String binaryToString(String binary) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteString = binary.substring(i, i + 8);
            text.append((char) Integer.parseInt(byteString, 2));
        }
        return text.toString();
    }
    
    private static String stringToHex(String input) {
        StringBuilder hex = new StringBuilder();
        for (char c : input.toCharArray()) {
            hex.append(String.format("%02X ", (int) c));
        }
        return hex.toString().trim();
    }
    
    private static String stringToDecimal(String input) {
        StringBuilder decimal = new StringBuilder();
        for (char c : input.toCharArray()) {
            decimal.append((int) c).append(" ");
        }
        return decimal.toString().trim();
    }
}