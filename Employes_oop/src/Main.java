 class Employee{
 private String name;
 private double salary;
 private int workHours;
 private int hireYear;


 public Employee(String new_name, double new_salary, int new_workHours,int new_hireYear)
 {
     this.name=new_name;
     this.salary=new_salary;
     this.workHours=new_workHours;
     this.hireYear=new_hireYear;

 }

     public double getSalary() {
         return salary;
     }
     public void setSalary(double new_salary)
     {
         salary=new_salary ;
     }

     public double tax()
 { if(this.salary>1000)
   {
       return this.salary*0.03;
   }else
     return 0.0;
 }
 public double bonus()
 { int extraworks=this.workHours-40;

     return extraworks*30;
 }
 public double increase()
 { int year = 2020-this.hireYear;
     if (year<10) {
         return this.salary * 0.5;
     } else if (year>=10 && year<20) {
         return this.salary*0.10;
     }else
         return this.salary*0.15;
 }

 }
public class Main {
    public static void main(String[] args) {
 Employee emp = new Employee("Arda",2000.0,45,1985);

System.out.println("Tax alınan vergi : "+emp.tax());
System.out.println("BONUS  : "+emp.bonus());
System.out.println("İncrase : "+emp.increase());
System.out.println(  emp.bonus()+emp.getSalary()- emp.tax());
System.out.println(emp.increase()+emp.getSalary());


    }
}