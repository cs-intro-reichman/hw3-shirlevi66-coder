/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	
    public static boolean isAnagram(String str1, String str2) {

   	String s1 = preProcess(str1).replace(" ", "");
	String s2 = preProcess(str2).replace(" ", "");

	if (s1.length() != s2.length()) return false;
	if (s1.length() == 0) return true;

	int[] counts = new int[26];

	for (int i = 0; i < s1.length(); i++) {
    char c1 = s1.charAt(i);
    	if (c1 >= 'a' && c1 <= 'z') counts[c1 - 'a']++;
	}

	for (int i = 0; i < s2.length(); i++) {
    char c2 = s2.charAt(i);
    if (c2 >= 'a' && c2 <= 'z') counts[c2 - 'a']--;
	}

	for (int c : counts) {
    if (c != 0) return false;
	}

	return true;
	}
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
	   StringBuilder result = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);

        if (Character.isLetter(c)) {
            result.append(Character.toLowerCase(c));
        } else if (c == ' ') {
            result.append(' ');
        }
    }
    return result.toString();
}
	
	public static String randomAnagram(String str) {
		 char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int randomIndex = (int)(Math.random() * chars.length);
            char temp = chars[i];
            chars[i] = chars[randomIndex];
            chars[randomIndex] = temp;
        }
        return new String(chars);
    }
}