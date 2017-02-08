package net.sepp_tember.lib.randomizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class DelegationTester<T extends D, D> {

	public interface Void0ParametersExecutor<T> {
		void execute(T testObject);
	}

	public interface Returning0ParametersExecutor<T, R> {
		R execute(T testObject);
	}

	public interface Void1ParametersExecutor<T, P1> {
		void execute(T testObject, P1 parameter1);
	}

	public interface Returning1ParametersExecutor<T, R, P1> {
		R execute(T testObject, P1 parameter1);
	}

	public interface ReturningIntExecutor<T, R> {
		R execute(T testObject, int parameter1);
	}

	public interface Void2ParametersExecutor<T, P1, P2> {
		void execute(T testObject, P1 parameter1, P2 parameter2);
	}

	public interface Returning2ParametersExecutor<T, R, P1, P2> {
		R execute(T testObject, P1 parameter1, P2 parameter2);
	}

	private final Supplier<T> testeeSupplier;
	private final BiConsumer<T, D> delegateInjector;
	private final Class<D> delegateType;

	public DelegationTester(Supplier<T> testeeSupplier, BiConsumer<T, D> delegateInjector, Class<D> delegateType) {
		this.testeeSupplier = testeeSupplier;
		this.delegateInjector = delegateInjector;
		this.delegateType = delegateType;
	}

	public <R> void testReturningCall(Returning0ParametersExecutor<D, R> executor, R output) {
		T testee = testeeSupplier.get();
		D delegate = mock(delegateType);
		delegateInjector.accept(testee, delegate);
		when(executor.execute(delegate)).thenReturn(output);
		R actualOutput = executor.execute(testee);
		assertEquals(output, actualOutput);
		executor.execute(verify(delegate));
	}

	public void testVoidCall(Void0ParametersExecutor<D> executor) {
		T testee = testeeSupplier.get();
		D delegate = mock(delegateType);
		delegateInjector.accept(testee, delegate);
		executor.execute(testee);
		executor.execute(verify(delegate));
	}

	public <R, P1> void testReturningCall(Returning1ParametersExecutor<D, R, P1> executor, R output, P1 parameter1) {
		T testee = testeeSupplier.get();
		D delegate = mock(delegateType);
		delegateInjector.accept(testee, delegate);
		when(executor.execute(delegate, parameter1)).thenReturn(output);
		R actualOutput = executor.execute(testee, parameter1);
		assertEquals(output, actualOutput);
		executor.execute(verify(delegate), parameter1);
	}

	public <R> void testReturningCall(ReturningIntExecutor<D, R> executor, R output, int parameter1) {
		T testee = testeeSupplier.get();
		D delegate = mock(delegateType);
		delegateInjector.accept(testee, delegate);
		when(executor.execute(delegate, parameter1)).thenReturn(output);
		R actualOutput = executor.execute(testee, parameter1);
		assertEquals(output, actualOutput);
		executor.execute(verify(delegate), parameter1);
	}

	public <P1> void testVoidCall(Void1ParametersExecutor<D, P1> executor, P1 parameter1) {
		T testee = testeeSupplier.get();
		D delegate = mock(delegateType);
		delegateInjector.accept(testee, delegate);
		executor.execute(testee, parameter1);
		executor.execute(verify(delegate), parameter1);
	}

	public <R, P1, P2> void testReturningCall(Returning2ParametersExecutor<D, R, P1, P2> executor, R output, P1 parameter1,
			P2 parameter2) {
		T testee = testeeSupplier.get();
		D delegate = mock(delegateType);
		delegateInjector.accept(testee, delegate);
		when(executor.execute(delegate, parameter1, parameter2)).thenReturn(output);
		R actualOutput = executor.execute(testee, parameter1, parameter2);
		assertEquals(output, actualOutput);
		executor.execute(verify(delegate), parameter1, parameter2);
	}

	public <P1, P2> void testVoidCall(Void2ParametersExecutor<D, P1, P2> executor, P1 parameter1, P2 parameter2) {
		T testee = testeeSupplier.get();
		D delegate = mock(delegateType);
		delegateInjector.accept(testee, delegate);
		executor.execute(testee, parameter1, parameter2);
		executor.execute(verify(delegate), parameter1, parameter2);
	}
}
