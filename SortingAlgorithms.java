import java.util.Random;

public class SortingAlgorithms {
	
	// Var1 1000/10000/100000/1000000
	// Var2 10/100/1000/10000/100000/1000000
	
	public static void main(String[] args){
		Random rand = new Random();
		
		int[] var1 = {1000,10000,100000,1000000};
		int[] var2 = {100,100,1000,10000,100000,1000000};
		int count = 0;
		
		
		///*
		int choice = 1;
		
		
		while(choice < 6){
			for(int i = 0; i < var1.length; ++i){
				System.out.println(var1[i]+": ArraySize\n");
				for (int j = 0; j<var2.length; ++j){
					
					int[] list = new int[var1[i]];
					//Random rand = new Random(var2[j]);
					for (int k = 0; k < list.length; ++k) {
						list[k] = rand.nextInt(var2[j]);
					}
					
					long initTime = System.currentTimeMillis();
					System.out.print("Rand(0 - "+(var2[j]-1)+"): ");
					//System.out.print((count%24+1) + ":");
					choice(choice,list);
					long finalTime = System.currentTimeMillis();
					System.out.println(finalTime - initTime);
					++count;
				}
				System.out.println();
			}
			System.out.println("\n---\tNEW SORT\t---\n");
			
			++choice;
		}
		/*
		*/
		System.out.println("\n---\tNEW SORT\t---\n");
		/*
		///Radix Sort
		for(int i = 0; i < var1.length; ++i){
			System.out.println(var1[i]+": ArraySize\n");
			for (int j = 0; j < var2.length; ++j){
				 
				int[] list = new int[var1[i]];
				
				for (int k = 0; k < list.length; ++k) {
					list[k] = rand.nextInt(var2[j]);
				}
				
				long initTime = System.currentTimeMillis();
				System.out.print("Rand(0 - "+(var2[j]-1)+"): ");
				System.out.print("radixSort: ");
				
				radixSort(list,j+1);
				
				long finalTime = System.currentTimeMillis();
				System.out.println(finalTime - initTime);
				++count;
			}
		}
		*/
		//counting sort.
		/*
		for(int i = 0; i < var1.length; ++i){
			System.out.println(var1[i]+": ArraySize\n");
			for (int j = 0; j < var2.length; ++j){
				 
				int[] list = new int[var1[i]];
				//Random rand = new Random(var2[j]);
				for (int k = 0; k < list.length; ++k) {
					list[k] = rand.nextInt(var2[j] );
				}
				
				long initTime = System.currentTimeMillis();
				System.out.print("Rand(0 - "+(var2[j]-1)+"): ");
				System.out.print("Selecton Sort: ");
				
				quickSort(list);
				
				long finalTime = System.currentTimeMillis();
				System.out.println(finalTime - initTime);
				++count;
			}
			System.out.println();
		}*/
		// heap sort
		/*
		int[] list = new int[1000000];
	
		for (int i = 0; i < list.length; ++i) {
			list[i] = rand.nextInt(100);
		}

		long initTime = System.currentTimeMillis();
		quickSort(list);
		long finalTime = System.currentTimeMillis();

		System.out.println(finalTime - initTime);
		*/
				
	}
	
	//Bubble Sort, Selection Sort, Insertion Sort, Heap Sort, Merge Sort,
	//Quick Sort, Counting Sort, and Radix Sort
	
	//heap. radix. counting.
	
	public static void choice(int n, int[] numbers){
		 switch(n){
		 case 1:
			 System.out.print("quickSort: ");
			 quickSort(numbers);
			 break;
		 case 2: 
			 System.out.print("mergeSort: ");
			 mergeSort(numbers);
			 break;
		 case 3:
			 System.out.print("countingSort: ");
			 countingSort(numbers);
			 break;
		 case 4:
			 System.out.print("insertionSort: ");
			 insertionSort(numbers);
			 break;
		 case 5:
			 System.out.print("selectionSort: ");
			 selectionSort(numbers);
			 break;
		 case 6:
			 System.out.print("bubbleSort: ");
			 bubbleSort(numbers);
			 break;
		 }
		 
	}
	
	
	
	public static int[] countingSort(int[] numbers) {
		int[] counter = new int[100000000];
		int[] result = new int[numbers.length];

		for (int i = 0; i < numbers.length; ++i) {
			 ++counter[numbers[i]];
		}

		for (int i = 1; i < counter.length; ++i) {
			counter[i] += counter[i - 1];
		}

		for (int i = 0; i < result.length; ++i) {
			result[--counter[numbers[i]]] = numbers[i];
		}

		return result;
	}

	
	public static int[] radixSort(int[] numbers, int radix) {
		int[] result = numbers;
		
		for (int pos = 1; pos <= radix; ++pos) {
			result = modCountingSort(result, pos);
		}
		
		return result;
	}
	
	private static int getDigit(int number, int pos) {
		int pow = (int)Math.pow(10, pos);
		int rem = number % pow;
		return rem / (pow / 10);
	}

	public static int[] modCountingSort(int[] numbers, int pos) {
		int[] counter = new int[10];
		int[] result = new int[numbers.length];

		for (int i = 0; i < numbers.length; ++i) {
			++counter[getDigit(numbers[i], pos)];
		}

		for (int i = 1; i < counter.length; ++i) {
			counter[i] += counter[i - 1];
		}

		for (int i = result.length-1; i >= 0; --i) {
			result[--counter[getDigit(numbers[i], pos)]] = numbers[i];
		}

		return result;
	}

	public static int[] quickSort(int[] numbers) {

		quickSortHelper(numbers, 0, numbers.length - 1);

		return numbers;
	}

	private static void quickSortHelper(int[] numbers, int init, int last) {

		if ((last - init) < 1 || (last < 0)) {
			return;
		}

		int pivotIndex = partitionList(numbers, init, last);

		quickSortHelper(numbers, init, pivotIndex - 1);
		quickSortHelper(numbers, pivotIndex + 1, last);

	}

	private static int partitionList(int[] numbers, int init, int last) {
		int lastAssignedPos = init;

		for (int i = init; i < last; ++i) {
			if (numbers[i] < numbers[last]) {
				swap(numbers, lastAssignedPos, i);
				++lastAssignedPos;
			}
		}

		swap(numbers, last, lastAssignedPos);
		return lastAssignedPos;
	}

	public static int[] mergeSort(int[] numbers) {

		return mergeSortHelper(numbers, 0, numbers.length - 1);
	}

	private static int[] mergeSortHelper(int[] numbers, int init, int last) {
		if ((last - init) == 0) {
			return new int[] { numbers[last] };
		}

		int mid = (last + init) / 2;

		int[] subList1 = mergeSortHelper(numbers, init, mid);
		int[] subList2 = mergeSortHelper(numbers, mid + 1, last);

		return merge(subList1, subList2);
	}

	private static int[] merge(int[] subList1, int[] subList2) {
		int[] result = new int[subList1.length + subList2.length];
		int sub1First = 0;
		int sub2First = 0;

		for (int i = 0; i < result.length; ++i) {
			if (sub1First == subList1.length) {
				result[i] = subList2[sub2First++];
			} else if (sub2First == subList2.length) {
				result[i] = subList1[sub1First++];
			} else if (subList1[sub1First] <= subList2[sub2First]) {
				result[i] = subList1[sub1First++];
			} else {
				result[i] = subList2[sub2First++];
			}
		}

		return result;
	}

	public static int[] bubbleSort(int[] numbers) {
		boolean swapped = false;

		do {
			swapped = false;

			for (int i = 0; i < (numbers.length - 1); ++i) {
				if (numbers[i] > numbers[i + 1]) {
					swap(numbers, i, i + 1);
					swapped = true;
				}
			}

		} while (swapped);

		return numbers;
	}

	public static int[] selectionSort(int[] numbers) {

		for (int i = 0; i < numbers.length - 1; ++i) {
			int minIndex = i;
			//System.out.println("still here");
			for (int j = i + 1; j < numbers.length; ++j) {
				if (numbers[j] < numbers[minIndex])
					minIndex = j;
			}
			//System.out.println("still here");
			swap(numbers, minIndex, i);
		}

		return numbers;
	}

	public static int[] insertionSort(int[] numbers) {
		for (int i = 1; i < numbers.length; ++i) {
			for (int j = i; j > 0; --j) {
				if (numbers[j] < numbers[j - 1]) {
					swap(numbers, j, j - 1);
					//System.out.print("still here");
				} else {
					break;
				}
			}
		}

		return numbers;
	}

	private static void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}

	public static int[] recInsertSort(int[] list) {
		return recSortHelper(0, list);
	}

	private static int[] insert(int init, int[] list) {
		for (int i = init; i < list.length - 1; ++i) {
			if (list[i] > list[i + 1]) {
				swap(list, i, i + 1);
			} else {
				break;
			}
		}

		return list;
	}

	private static int[] recSortHelper(int init, int[] list) {
		if (init == (list.length - 1)) {
			return list;
		} else {
			return insert(init, recSortHelper(init + 1, list));
		}
	}

}
