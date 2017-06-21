
public class Movie {
private int year;
private String name;
private double total;
/**
 * @return the year
 */
public int getYear() {
	return year;
}
/**
 * @param year the year to set
 */
public void setYear(int year) {
	this.year = year;
}
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return the total
 */
public double getTotal() {
	return total;
}
/**
 * @param total the total to set
 */
public void setTotal(double total) {
	this.total = total;
}
/*public Movie(int year, String name, double total) {
	super();
	this.year = year;
	this.name = name;
	this.total = total;
}
public Movie() {
	// TODO Auto-generated constructor stub
}*/
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "Movie [year=" + year + ", name=" + name + ", total=" + total + "]";
}
/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + year;
	return result;
}
/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Movie other = (Movie) obj;
	if (year != other.year)
		return false;
	return true;
}




	
}
