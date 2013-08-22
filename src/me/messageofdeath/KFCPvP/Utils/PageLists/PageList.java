package me.messageofdeath.KFCPvP.Utils.PageLists;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;

public class PageList {
	
	private ArrayList<Option> options = new ArrayList<Option>();
	private int amountPerPage;
	
	public PageList(ArrayList<Option> options, int amountPerPage) {
		this.options = options;
		this.amountPerPage = amountPerPage;
	}
	
	public ArrayList<String> getOptions(CommandSender name, int page) {		
		ArrayList<String> texts = new ArrayList<String>();
		int id = page * this.amountPerPage;
		for(int i = id; i < id + this.amountPerPage; i++) {
			if(i >= this.options.size())
    			break;
			if(this.options.get(i).hasPermission(name))
				texts.add(this.options.get(i).getText());
		}
		return texts;
	}
	
	public int checkPage(CommandSender name, int page) {
		if(page >= Integer.MAX_VALUE)
			page = Integer.MAX_VALUE - 1;
		if(page <= Integer.MIN_VALUE)
			page = Integer.MIN_VALUE + 1;
		
		page -= 1;
		if(page < 0) {
			page = 0;
		}
		if(page > getTotalPages(name) - 1) {
			page = getTotalPages(name) - 1;
		}
		return page;
	}
	
	public int getTotalPages(CommandSender name) {
		if(!this.options.isEmpty()) {
			int text = 0;
			for(Option option : this.options)
				if(option.hasPermission(name))
					text++;
			if(text % this.amountPerPage == 0)
				return (text / this.amountPerPage);
			else if(text % this.amountPerPage > 0)
				return (text / this.amountPerPage) + 1;
			else
				return 0;
		}else
			return 0;
	}
}
