package me.messageofdeath.KFCPvP.Utils.PageLists;

import java.util.ArrayList;

public class PageList {
	
	private ArrayList<Option> options = new ArrayList<Option>();
	private int amountPerPage;
	
	public PageList(ArrayList<Option> options, int amountPerPage) {
		this.options = options;
		this.amountPerPage = amountPerPage;
	}
	
	public ArrayList<String> getOptions(String name, int page) {
		checkPage(name, page);
		
		ArrayList<String> texts = new ArrayList<String>();
		int id = page * this.amountPerPage;
		for(int i = id; i < id + this.amountPerPage; i++)
			texts.add(this.options.get(i).getText());
		return texts;
	}
	
	private int checkPage(String name, int page) {
		page -= 1;
		if(page == -1)
			page = 1;
		if(page > getTotalPages(name))
			page = getTotalPages(name);
		return page;
	}
	
	public int getTotalPages(String name) {
		if(!this.options.isEmpty()) {
			int text = 0;
			for(Option option : this.options)
				if(option.hasPermission(name))
					text++;
			if(text % this.amountPerPage > 0)
				return (text / this.amountPerPage) + 1;
			else
				return (text / this.amountPerPage);
		}else
			return 0;
	}
}
