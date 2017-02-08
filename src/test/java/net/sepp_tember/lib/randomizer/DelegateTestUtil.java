package net.sepp_tember.lib.randomizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public final class DelegateTestUtil {
	private DelegateTestUtil() {
	}

	public static <T extends D, D, C extends D, R, P1> void testDelegate(Supplier<T> testeeSupplier, BiConsumer<T, D> injector,
			Class<C> delegateType, Returning1ParametersExecutor<D, R, P1> executor, R output, P1 parameter1) {
		T testee = testeeSupplier.get();
		D delegate = mock(delegateType);
		injector.accept(testee, delegate);
		when(executor.execute(delegate, parameter1)).thenReturn(output);
		R actualOutput = executor.execute(testee, parameter1);
		assertEquals(output, actualOutput);
		executor.execute(verify(delegate), parameter1);
	}

	public static <T extends D, D, R, P1, P2> void testDelegate(Supplier<T> testeeSupplier, BiConsumer<T, D> injector,
			Class<D> delegateType, Returning2ParametersExecutor<D, R, P1, P2> executor, R output, P1 parameter1, P2 parameter2) {
		T testee = testeeSupplier.get();
		D delegate = mock(delegateType);
		injector.accept(testee, delegate);
		when(executor.execute(delegate, parameter1, parameter2)).thenReturn(output);
		R actualOutput = executor.execute(testee, parameter1, parameter2);
		assertEquals(output, actualOutput);
		executor.execute(verify(delegate), parameter1, parameter2);
	}

	public interface Returning1ParametersExecutor<T, R, P1> {
		R execute(T testObject, P1 parameter1);
	}

	public interface Returning2ParametersExecutor<T, R, P1, P2> {
		R execute(T testObject, P1 parameter1, P2 parameter2);
	}
}
