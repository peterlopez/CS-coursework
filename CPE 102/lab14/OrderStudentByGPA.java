public class OrderStudentByGPA implements Comparator<Student>
{
   private Comparator<Student> next;
   
   public OrderStudentByGPA(Comparator<Student> next) {
      this.next = next;
   }
   public OrderStudentByGPA() {}
   
   public int compare(Student a, Student b) {
      double gpaA = a.getGPA();
      double gpaB = b.getGPA();
      
      if(gpaA > gpaB) return 1;
      else if(gpaA < gpaB) return -1;
      else if(next == null) return 0;
      else return next.compare(a, b);
   }
}