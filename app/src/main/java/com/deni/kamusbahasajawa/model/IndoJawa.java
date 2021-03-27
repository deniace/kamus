package com.deni.kamusbahasajawa.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


/**
 * Created by Deni Supriyatna on 16 - Oct - 2019.
 */
@Table(name = "IndoJawa")
public class IndoJawa extends Model{

    @Column(name = "indo")
    private String indo;
    @Column(name = "jawa")
    private String jawa;

    public IndoJawa(){}

    public IndoJawa(String indo, String jawa){
        this.indo = indo;
        this.jawa = jawa;
    }

    public String getIndo() {
        return indo;
    }

    public void setIndo(String indo) {
        this.indo = indo;
    }

    public String getJawa() {
        return jawa;
    }

    public void setJawa(String jawa) {
        this.jawa = jawa;
    }
}
