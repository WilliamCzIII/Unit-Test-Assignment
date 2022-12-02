import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class TestDemoTest {

	private TestDemo testDemo = new TestDemo();
	
	@BeforeEach
	void setUp() throws Exception {
	TestDemo testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource ( "TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		}
		else {
			assertThatThrownBy( () -> 
					testDemo.addPositive(a, b))
						.isInstanceOf(IllegalArgumentException.class);
		}
	}
	
	static  Stream<Object> argumentsForAddPositive() {
		return Stream.of(
		arguments( 2, 4, 6, false),
		arguments( 1, 8, 9, false),
		arguments( 3, 7, 10, false),
		arguments( 0, 0, 0, false),
		arguments( -1, -3, -4, false),
		arguments( 7, 25, 32, false),
		arguments( 54, 26, 80, false),
		arguments( 0, 1, 1, false),
		arguments( 3, 0, 3, false),
		arguments( -1, 4, 3, false),
		arguments( 1, -8, -7, false),
		arguments( 1, 8, 60, false)
		);
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
		
	}
}
