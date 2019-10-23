package com.tarena.fly;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator<T> implements Iterator<T> {
	private int index = 0;
	private T[] data;

	
	public MyIterator(T[] data){
		setData(data);
	}
	

	private void setData(T[] data2) {
		this.data = data2;
        index = 0;
	}


	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return index < data.length;
	}

	@Override
	public T next() {
		if (hasNext()) {
            return data[index++];
        }
		return (T) new NoSuchElementException();
	}

}
