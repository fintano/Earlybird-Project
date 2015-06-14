package kr.co.lguplus.last;

import java.util.List;

import kr.co.lguplus.last.Item;

public interface OnFinishSearchListener {
	public void onSuccess(List<Item> itemList);
	public void onFail();
}
