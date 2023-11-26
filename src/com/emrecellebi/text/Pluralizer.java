package com.emrecellebi.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.emrecellebi.util.Strings;

public final class Pluralizer
{
	static
	{
		Pluralizer plz = new Pluralizer();
		
		String[][] data1 = {{"this","these"}, {"that","those"}, {"echo","echoes"}, {"dingo","dingoes"}, {"volcano","volcanoes"}, {"tornado","tornadoes"}, {"torpedo","torpedoes"}, {"genus","genera"}, {"viscus","viscera"}, {"stigma","stigmata"}, {"stoma","stomata"}, {"dogma","dogmata"}, {"lemma","lemmata"}, {"anathema","anathemata"}, {"ox","oxen"}, {"axe","axes"}, {"die","dice"}, {"yes","yeses"}, {"foot","feet"}, {"eave","eaves"}, {"goose","geese"}, {"tooth","teeth"}, {"quiz","quizzes"}, {"human","humans"}, {"proof","proofs"}, {"carve","carves"}, {"valve","valves"}, {"looey","looies"}, {"thief","thieves"}, {"groove","grooves"}, {"pickaxe","pickaxes"}, {"whiskey","whiskies"}};
		for(String[] o : data1)
			plz.addIrregularRule(o[0], o[1]);
		
		String[][] data2 = {{"/s?$","s"}, {"/([^aeiou]ese)$","$1"}, {"/(ax|test)is$","$1es"}, {"/(alias|[^aou]us|t[lm]as|gas|ris)$","$1es"}, {"/(e[mn]u)s?$","$1s"}, {"/([^l]ias|[aeiou]las|[ejzr]as|[iu]am)$","$1"}, {"/(alumn|syllab|octop|vir|radi|nucle|fung|cact|stimul|termin|bacill|foc|uter|loc|strat)(?:us|i)$","$1i"}, {"/(alumn|alg|vertebr)(?:a|ae)$","$1ae"}, {"/(seraph|cherub)(?:im)?$","$1im"}, {"/(her|at|gr)o$","$1oes"}, {"/(agend|addend|millenni|medi|dat|extrem|bacteri|desiderat|strat|candelabr|errat|ov|symposi|curricul|automat|quor)(?:a|um)$","$1a"}, {"/(apheli|hyperbat|periheli|asyndet|noumen|phenomen|criteri|organ|prolegomen|hedr|automat)(?:a|on)$","$1a"}, {"/sis$","ses"}, {"/(?:(kni|wi|li)fe|(ar|l|ea|eo|oa|hoo)f)$","$1$2ves"}, {"/([^aeiouy]|qu)y$","$1ies"}, {"/([^ch][ieo][ln])ey$","$1ies"}, {"/(x|ch|ss|sh|zz)$","$1es"}, {"/(matr|cod|mur|sil|vert|ind|append)(?:ix|ex)$","$1ices"}, {"(m|l)(?:ice|ouse)","$1ice"}, {"/(pe)(?:rson|ople)$","$1ople"}, {"/(child)(?:ren)?$","$1ren"}, {"/eaux$","$0"}, {"/m[ae]n$","men"}};
		for(String[] o : data2)
			plz.addPluralRule(o[0], o[1]);
		
		String[][] data3 = {{"/(.)s$","$1"}, {"/([^aeiou]s)es$","$1"}, {"/(wi|kni|(?:after|half|high|low|mid|non|night|[^\\w]|^)li)ves$","$1fe"}, {"/(ar|(?:wo|[ae])l|[eo][ao])ves$","$1f"}, {"/ies$","y"}, {"/\\b([pl]|zomb|(?:neck|cross)?t|coll|faer|food|gen|goon|group|lass|talk|goal|cut)ies$","$1ie"}, {"/\\b(mon|smil)ies$","$1ey"}, {"(m|l)ice","$1ouse"}, {"/(seraph|cherub)im$","$1"}, {"/.(x|ch|ss|sh|zz|tto|go|cho|alias|[^aou]us|t[lm]as|gas|(?:her|at|gr)o|ris)(?:es)?$","$1"}, {"/(analy|^ba|diagno|parenthe|progno|synop|the|empha|cri)(?:sis|ses)$","$1sis"}, {"/(movie|twelve|abuse|e[mn]u)s$","$1"}, {"/(test)(?:is|es)$","$1is"}, {"/(x|ch|.ss|sh|zz|tto|go|cho|alias|[^aou]us|tlas|gas|(?:her|at|gr)o|ris)(?:es)?$","$1"}, {"/(e[mn]u)s?$","$1"}, {"/(cookie|movie|twelve)s$","$1"}, {"/(cris|test|diagnos)(?:is|es)$","$1is"}, {"/(alumn|syllab|octop|vir|radi|nucle|fung|cact|stimul|termin|bacill|foc|uter|loc|strat)(?:us|i)$","$1us"}, {"/(agend|addend|millenni|dat|extrem|bacteri|desiderat|strat|candelabr|errat|ov|symposi|curricul|quor)a$","$1um"}, {"/(apheli|hyperbat|periheli|asyndet|noumen|phenomen|criteri|organ|prolegomen|hedr|automat)a$","$1on"}, {"/(alumn|alg|vertebr)ae$","$1a"}, {"/(cod|mur|sil|vert|ind)ices$","$1ex"}, {"/(matr|append)ices$","$1ix"}, {"/(pe)(rson|ople)$","$1rson"}, {"/(child)ren$","$1"}, {"/(eau)x?$","$1"}, {"/men$","man"}};
		for(String[] o : data3)
			plz.addSingularRule(o[0], o[1]);
		
		String[] data4 = {"adulthood", "advice", "agenda", "aid", "alcohol", "ammo", "anime", "athletics", "audio", "bison", "blood", "bream", "buffalo", "butter", "carp", "cash", "chassis", "chess", "clothing", "cod", "commerce", "cooperation", "corps", "debris", "diabetes", "digestion", "elk", "energy", "equipment", "excretion", "expertise", "flounder", "fun", "gallows", "garbage", "graffiti", "headquarters", "health", "herpes", "highjinks", "homework", "housework", "information", "jeans", "justice", "kudos", "labour", "literature", "machinery", "mackerel", "mail", "media", "mews", "moose", "music", "news", "pike", "plankton", "pliers", "police", "pollution", "premises", "rain", "research", "rice", "salmon", "scissors", "series", "sewage", "shambles", "shrimp", "species", "staff", "swine", "tennis", "traffic", "transportation", "trout", "tuna", "wealth", "welfare", "whiting", "wildebeest", "wildlife", "you", "/[^aeiou]ese$/i", "/deer$", "/fish$", "/measles$", "/o[iu]s$", "/pox$", "/sheep$"};
		for(String o : data4)
			plz.addUncountableRule(o);
		
		PLURALIZER = plz;
	}
	
	public static final Pluralizer PLURALIZER;
	
	private final Map<String, String> irregularPlurals = new HashMap();
	private final Map<String, String> irregularSingles = new HashMap();
	private final List<Pair<Pattern, String>> pluralRules = new ArrayList<>();
	private final List<Pair<Pattern, String>> singularRules = new ArrayList<>();
	private final Set<String> uncountables = new HashSet();
	
	public void addIrregularRule(String single, String plural)
	{
		this.irregularSingles.put(single, plural);
		this.irregularPlurals.put(plural, single);
	}
	
	public void addPluralRule(String rule, String replacement)
	{
		this.pluralRules.add(Pair.create(sanitizeRule(rule), replacement));
	}

	public void addSingularRule(String rule, String replacement)
	{
		this.singularRules.add(Pair.create(sanitizeRule(rule), replacement));
	}

	public void addUncountableRule(String word)
	{
		if(!word.startsWith("/"))
			this.uncountables.add(word);
		else
		{
			addPluralRule(word, "$0");
			addSingularRule(word, "$0");
		}
	}
	
	public String plural(String word)
	{
		return restoreCase(word, replaceWord(word, this.irregularSingles, this.irregularPlurals, this.pluralRules));
	}
	
	public String pluralize(String word, int count, boolean inclusive)
	{
		String pluralized = (count == 1) ? singular(word) : plural(word);
		return (inclusive ? (count + " ") : "") + Strings.notNullize(pluralized, word);
	}
	
	public String singular(String word)
	{
		return restoreCase(word, replaceWord(word, this.irregularPlurals, this.irregularSingles, this.singularRules));
	}
	
	private String replaceWord(String word, Map<String, String> replaceMap, Map<String, String> keepMap, List<? extends Pair<Pattern, String>> rules)
	{
		if(Strings.isEmpty(word)) return word;
		if(keepMap.containsKey(word)) return word; 
		String replacement = replaceMap.get(word);
		if(replacement != null) return replacement; 
		return sanitizeWord(word, rules);
	}
	
	public static String restoreCase(String word, String result)
	{
		if(word == null || result == null || word == result) return result; 
		int len = Math.min(result.length(), word.length());
		if(len == 0) return result; 
		char[] chars = result.toCharArray();
		int i = 0;
		for(; i < len; i++)
		{
			char wc = word.charAt(i);
			if(chars[i] != wc || i == len - 1)
			{
				char uc = Character.toUpperCase(chars[i]);
				char lc = Character.toLowerCase(chars[i]);
				if(wc != lc && wc != uc) break; 
				chars[i] = wc;
			}
		}
		if(i > 0 && i < chars.length)
		{
			char wc = word.charAt(i - 1);
			char uc = Character.toUpperCase(wc);
			char lc = Character.toLowerCase(wc);
			if(uc != lc)
				for(; i < chars.length; i++) chars[i] = (wc == uc) ? Character.toUpperCase(chars[i]) : Character.toLowerCase(chars[i]);  
		}
		
		return new String(chars);
	}
	
	private static Pattern sanitizeRule(String rule)
	{
		return Pattern.compile(rule.startsWith("/") ? rule.substring(1) : ("^" + rule + "$"), 2);
	}
	
	private String sanitizeWord(String word, List<? extends Pair<Pattern, String>> rules)
	{
		if(Strings.isEmpty(word) || this.uncountables.contains(word)) return word; 
		int len = rules.size();
		while(--len > -1)
		{
			Pair<Pattern, String> rule = rules.get(len);
			Matcher matcher = ((Pattern)rule.first).matcher(word);
			if(matcher.find()) return matcher.replaceFirst((String)rule.second); 
		}
		return null;
	}
}