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
public class Buttoninfo {
    String Name;

    public Buttoninfo() {
    }

    public Buttoninfo(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "Buttoninfo{" + "Name=" + Name + '}';
    }
    
}
