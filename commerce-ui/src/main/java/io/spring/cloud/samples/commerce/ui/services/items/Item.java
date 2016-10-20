package io.spring.cloud.samples.commerce.ui.services.items;

public class Item {

    private Long id;

    private String name;

    private String description;

    private String category;

    public Item() {
    }

    public Item(Long id, String name, String description, String category) {
        this.id = id;
        this.name = name;
		this.description = description;
		this.category = category;
    }
	
    public Item (String str) {
    	int count = 0;
    	Long myid = 0L;
    	String mystr[] = new String[3];
    	for (int i=0; i<str.length(); i++) {
    		int start = -1;
    		int end = -1;
    		if (str.charAt(i) == ':') {
    			start = i;
    		}
    		if (str.charAt(i) == ',') {
    			end = i;
    		}
    		if (start >0 && end > 0) {
    			if (count == 0) {
    				myid = Long.parseLong(str.substring(start + 1, end));
    			} else {
    				mystr[count-1] = str.substring(start + 1, end);
    			}
    		}
			end = -1;
    	}
    	this.id = myid;
        this.name = mystr[0];
		this.description = mystr[1];
		this.category = mystr[2];
    }
    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
