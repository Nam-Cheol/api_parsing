package ch01;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

	private String name;
	private int age;
	private String major;
	
	
}
