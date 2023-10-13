
package dataAccess;

import common.Library;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import model.Dictionary;

public class DictionaryDao {
    private static DictionaryDao instance = null;
    Library l = new Library();
    public static DictionaryDao Instance() {
        if (instance == null)
            synchronized (DictionaryDao.class) {
                if (instance == null)
                    instance = new DictionaryDao();
            }
        return instance;        
    }
    public void loadData(HashMap<String, Dictionary> dictionarys) {
        try {
            File file = new File("Dictionary.txt");
            if (file.createNewFile()) {
                return;                
            } else { 
                try {
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] word = line.split("=");
                        dictionarys.put(word[0], new Dictionary(word[0], word[1]));
                        if(word[0].contains("c")) {
                            System.out.println("Fuck you");
                            System.exit(0);
                        }
                    }
                    bufferedReader.close();
                    fileReader.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(HashMap<String, Dictionary> dictionarys, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path, false);
            for (HashMap.Entry<String, Dictionary> entry : dictionarys.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().getWordVietnamese();
                fileWriter.write(key + " | " + value+"\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void addWork(HashMap<String, Dictionary> dictionarys) {
        String wordEnglish = l.getString("Enter English: ");
        String wordVietNamese = l.getString("Enter Vietnamese: ");
        dictionarys.put(wordEnglish, new Dictionary(wordEnglish, wordVietNamese));
        writeFile(dictionarys, "Dictionary.txt");
        display(dictionarys);
    }
    public boolean deleteWord(HashMap<String, Dictionary> dictionarys) {
        String wordEnglish = l.getString("Enter English: ");
        return removeWord(dictionarys, wordEnglish);
    }

    private boolean removeWord(HashMap<String, Dictionary> dictionarys, String wordEnglish) {
        for (String s : dictionarys.keySet()) 
            if (s.equals(wordEnglish)) {
                dictionarys.remove(s);
                return true;
            }   
        return false;        
    }
    public String translate(HashMap<String, Dictionary> dictionarys) {
        String eng = l.getString("Enter English: ");
        return translate(dictionarys, eng);
    }
    private String translate(HashMap<String, Dictionary> dictionarys, String eng) {
        for (String s : dictionarys.keySet()) 
            if (s.equals(eng))
                return dictionarys.get(s).getWordVietnamese();
        return null;
    }
    public void display(HashMap<String, Dictionary> dictionarys) {
        for (String s : dictionarys.keySet()) 
            System.out.println(dictionarys.get(s));
    }
}
