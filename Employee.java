public class Employee extends IEmployee implements IEmploye{
    private String name;
    private int monthlySalary;
    private String designation;
    private ArrayList<IEmployee> subordinateList = new ArrayList<IEmployee>();

   public Employee() {
    }
    public Employee(String name, int monthlySalary, String designation) {
        this.name = name;
        this.monthlySalary = monthlySalary;
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public ArrayList<IEmployee> getSubordinateList() {
        return subordinateList;
    }

    public void setSubordinateList(ArrayList<IEmployee> subordinateList) {
        this.subordinateList = subordinateList;
    }
    @Override
    public int getYearlySalary() {
        return monthlySalary*12;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void addSubordinate( IEmployee employee )
  {
    subordinateList.add(employee);
  }

  public void removeSubordinate( IEmployee employee )
  {
    subordinateList.remove(employee);
  }

  public ArrayList<IEmployee> getChilds()
  {
    return getSubordinateList();
  }
  Employee dave = new Employee("Dave", 200000, "CEO");
}