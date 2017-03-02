## randomizer

`randomizer` is a Java library meant to provide randomized lists, that means lists which provide iterators that retrieve the elements 
in a randomized order.


### Usage

A small example on how to use `WeightedRandomizedList` to get some elements in a random order:

	class Example {
		public int main(String[] args) {
			// create randomized list
			WeightedRandomizedList<Number> randomizedList = new WeightedRandomizedList<>();
		
			// fill it with some elements
			randomizedList.add(new WeightedElement<>(0.3, 23));
			randomizedList.add(new WeightedElement<>(0.6, 42));
			randomizedList.add(new WeightedElement<>(0.8, 12));
			
			// create randomized iterator
			Iterator<WeightedElement<Number>> iterator = randomizedList.randomizedIterator();
			
			for (int i = 0; i < 10000; i++) {
				// get elements in random order
				Number next = iterator.next().getElement();
				// then do something with the element
			}
		}
	}

The `WeightedRandomizedList` provides a randomized iterator that delivers an endless sequence of elements (i.e. `iterator.hasNext()` 
always returns `true`). The distribution of the elements is weighted; the higher the weight, the more often the elements gets drawn. The 
total sum of the weights in the list does not have to be 1, each weight gets normalized by the total sum of weights. So in the above 
example the number 23 gets drawn approximately 1765 times, 42 3529 times, and 12 4706 times. Of course the exact number can not be 
foreseen, because the drawing of the elements is based on a random number generator.
