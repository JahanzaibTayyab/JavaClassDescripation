/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayListData;

/**
 *
 * @author Admin
 */
public class constrauctor {
    public String Name;
    public String Arguments;
    public String type;
    public String contype;

    public constrauctor() {
    }

    public constrauctor(String Name, String Arguments, String type) {
        this.Name = Name;
        this.Arguments = Arguments;
        this.type = type;
    }

    public constrauctor(String Name, String Arguments, String type, String contype) {
        this.Name = Name;
        this.Arguments = Arguments;
        this.type = type;
        this.contype = contype;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getArguments() {
        return Arguments;
    }

    public void setArguments(String Arguments) {
        this.Arguments = Arguments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContype() {
        return contype;
    }

    public void setContype(String contype) {
        this.contype = contype;
    }

    @Override
    public String toString() {
        return "Constructor Information{" + "Name:" + Name + ", Arguments:" + Arguments + ", Scope:" + type + ", Type:" + contype + '}';
    }

    
    
}
