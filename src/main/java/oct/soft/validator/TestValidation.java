package oct.soft.validator;

import oct.soft.model.Office;

public class TestValidation {

	public static void main(String[] args) {
	 
		Office office = new Office("ss", 1, 2);
		String errors = MyValidator.validate(office);
		System.out.println(errors);

	}

}
