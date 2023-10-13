
package controller;

import java.util.HashMap;
import model.Dictionary;
import repository.DictionaryRepository;
import repository.IDictionaryRepository;
import view.Menu;

public class DictionaryManagement extends Menu<String>{
    private IDictionaryRepository dictionaryRepository;
    static String[] options = {"Add Word", "Delete Word", "Translate", "Exit"};
    HashMap<String, Dictionary> dictionarys;
    public DictionaryManagement() {
        super("===== DICTIONARY PROGRAM =====", options);
        dictionaryRepository = new DictionaryRepository();
        dictionarys = new HashMap<>();
    }
    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                dictionaryRepository.addWord(dictionarys);
                break;
            case 2 :
                dictionaryRepository.removeWord(dictionarys);
                break;
                
            case 3 :
                dictionaryRepository.translate(dictionarys);
                break;
            case 4 :
                System.exit(0);
                break;
        }
    }
}
