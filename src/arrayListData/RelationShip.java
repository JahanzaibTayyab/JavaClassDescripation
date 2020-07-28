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
public class RelationShip {
    String classname;
    String objectName;
    String Arguments;

    public RelationShip() {
    }

    public RelationShip(String classname, String objectName, String Arguments) {
        this.classname = classname;
        this.objectName = objectName;
        this.Arguments = Arguments;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getArguments() {
        return Arguments;
    }

    public void setArguments(String Arguments) {
        this.Arguments = Arguments;
    }

    @Override
    public String toString() {
        return "RelationShip{" + "ClassName:" + classname + ", Arguments=" + Arguments + '}';
    }
    
}
