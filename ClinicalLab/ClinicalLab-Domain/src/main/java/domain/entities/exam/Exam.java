package domain.entities.exam;

public class Exam {
    private ExamId id;
    private String name;
    private String requirements;
    private Double price;
    private int analysisTime;
    
    
	public Exam(ExamId id, String name, String requirements, Double price, int analysisTime) {
		super();
		this.id = id;
		this.name = name;
		this.requirements = requirements;
		this.price = price;
		this.analysisTime = analysisTime;
	}
	
	
	public ExamId getId() {
		return id;
	}
	public void setId(ExamId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRequirements() {
		return requirements;
	}
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getAnalysisTime() {
		return analysisTime;
	}
	public void setAnalysisTime(int analysisTime) {
		this.analysisTime = analysisTime;
	}

    
}