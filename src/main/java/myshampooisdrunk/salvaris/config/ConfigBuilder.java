package myshampooisdrunk.salvaris.config;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ConfigBuilder {
    private List<String> lines = new ArrayList<>();
    private final List<ConfigItem> items = new ArrayList<>();
    private final File file;
    public ConfigBuilder(File fp){
        file = fp;
    }
    public void comment(String comm){
        if(comm.equals("")) lines.add("");
        else lines.add("[" + comm + "]");
    }
    public <K> ConfigItem<K> add(String id, K item){
        String line = id + " = " + item;
        lines.add(line);
        ConfigItem<K> c = new ConfigItem<>(id,item);
        items.add(c);
        return c;
    }
    public <K> ConfigItem<K> add(String id, K item, String comment){
        String line = id + " = " + item +" # " + comment;
        lines.add(line);
        ConfigItem<K> c = new ConfigItem<>(id,item,comment);
        items.add(c);
        return c;
    }
    public <K> ConfigItem<K> add(String id, K item, String comment, K min, K max){
        String line = id + " = " + item +" # " + comment + " (min value: " + min + ", max value: " + max + ")";
        lines.add(line);
        ConfigItem<K> c = new ConfigItem<>(id,item,comment,min,max);
        items.add(c);
        return c;
    }

    private void write() {
        try {
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void read() {
        if(!file.exists()){
            write();
        }
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        }catch (IOException e){
            e.printStackTrace();
        }
        items.forEach(i -> lines.forEach(j -> {
            if(j.contains(i.getId() + " = ")){
                i.parse(j.split(" = ")[1].split(" # ")[0]);
            }
        }));

    }

}
