package myshampooisdrunk.salvaris.config;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ConfigItem <K> {
    private K val;
    private String comm;
    private String id;

    @Nullable
    private K min;
    @Nullable
    private K max;

    public ConfigItem(String id, K value, String comment, @Nullable K min, @Nullable K max){
        this.id = id;
        val = value;
        comm = comment;
        this.min = min;
        this.max = max;
    }
    public ConfigItem(String id, K value, String comment){
        this(id,value,comment,null,null);
    }
    public ConfigItem(String id, K value){
        this(id,value,"",null,null);
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }
    public void setVal(K value){
        val = value;
    }
    public K get(){
        return val;
    }
    public void setComm(String comment){
        comm = comment;
    }
    public String getComm(){
        return comm;
    }
    public void setMinMax(K min, K max){
        this.min = min;
        this.max = max;
    }
    public ArrayList<K> getMinMax(){
        if(min == null || max == null){
            return null;
        }
        return new ArrayList<>(List.of(min,max));
    }

    public void parse(String s) {
        if(val instanceof Boolean b){
            b = Boolean.parseBoolean(s);
            setVal((K)b);
        }else if(val instanceof Number n){
            try {
                n = NumberFormat.getInstance().parse(s);
            }
            catch (ParseException e){e.printStackTrace();}
            setVal((K)n);
        }else if(val instanceof String){
            setVal((K)s);
        }
    }
}
