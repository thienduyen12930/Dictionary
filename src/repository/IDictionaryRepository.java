
package repository;

import java.util.HashMap;
import model.Dictionary;

public interface IDictionaryRepository {
    void addWord(HashMap<String, Dictionary> dictionarys);
    void removeWord(HashMap<String, Dictionary> dictionarys);
    void translate(HashMap<String, Dictionary> dictionarys);
}
