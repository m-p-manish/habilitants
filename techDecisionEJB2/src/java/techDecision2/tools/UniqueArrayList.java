/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision2.tools;
import java.util.ArrayList;
import java.util.Collection;
/**
 *
 * @author Matthew Taylor
 */
public class UniqueArrayList extends ArrayList {
    /**
     * Only add the object if there is not
     * another copy of it in the list
     */
    private Object lastEqual;

    public Object getLastEqual() {
        return lastEqual;
    }
    @Override
    public boolean add(Object obj) {
        for (int i = 0; i < size(); i++) {
            if (obj.equals(get(i))) {
                lastEqual = get(i);
                return false;
            }
        }
        return super.add(obj);
    }

    @Override
    public boolean addAll(Collection c) {
        boolean result = true;
        for (Object t : c) {
            if (!add(t)) {
                result = false;
            }
        }
        return result;
    }
}
