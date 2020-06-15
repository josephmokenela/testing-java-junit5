package guru.springframework.sfgpetclinic.fauxspring;

import java.util.HashMap;
import java.util.Map;

public class ModelMapImpl implements Model {

    private Map<String, Object> modelMap = new HashMap<>();

    @Override
    public void addAttribute(String key, Object object) {
        modelMap.put(key, object);
    }

    @Override
    public void addAttribute(Object object) {
        // Do nothing for now
    }

    public Map<String, Object> getModelMap() {
        return modelMap;
    }
}
