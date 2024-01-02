package com.shares.baltic_nasdaq.service;

import com.shares.baltic_nasdaq.Model.Share;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
public class ShareService {
	public List<Share> convertNastaqResponse() {

		LocalDate date = LocalDate.now();
		WebClient webClient = WebClient.create("https://nasdaqbaltic.com/statistics/en/shares?date=" + date);
		Mono<String> response = webClient.get().retrieve().bodyToMono(String.class);
		Document doc = Jsoup.parse(response.block(), "UTF-8");
		List<Share> shares = new ArrayList<>();

		Element content = doc.getElementById("shares_form");
		Elements rows = content.getElementsByTag("tr");

		for (var row : rows) {
			Element td = row.select("td").first();
			if ((td == null) || (td.attr("data-sort-by").length() == 0)) {
				continue;
			}
			Elements tds = row.getElementsByTag("td");
			Share share = new Share();
			share.setCompany(tds.get(0).select("a").first().text());
			share.setTicker(tds.get(1).select("a").text());
			if ((tds.get(2).text().trim().length() != 0)
					&& !"-".equals(tds.get(2).text().trim())) {
				share.setLastPrice(Double.valueOf(tds.get(2).text()));
			}
			if ((tds.get(3).text().trim().length() != 0)
					&& !"-".equals(tds.get(3).text().trim())) {
				share.setChange(Double.valueOf(tds.get(3).text()));
			}
			if (tds.get(4).text().trim().length() != 0
					&& !"-".equals(tds.get(4).text().trim())) {
				share.setPercentage(Double.valueOf(tds.get(4).text()));
			}
			if (tds.get(5).text().trim().length() != 0
					&& !"-".equals(tds.get(5).text().trim())) {
				share.setBid(Double.valueOf(tds.get(5).text()));
			}
			if (tds.get(6).text().trim().length() != 0
					&& !"-".equals(tds.get(6).text().trim())) {
				share.setAsk(Double.valueOf(tds.get(6).text()));
			}
			try {
				share.setTrades(NumberFormat.getNumberInstance(Locale.US).parse(tds.get(7).text()).intValue());
			} catch (ParseException e) {
				throw new NumberFormatException();
			}
			try {
				share.setVolume(NumberFormat.getNumberInstance(Locale.US).parse(tds.get(8).text()).intValue());
			} catch (ParseException e) {
				throw new NumberFormatException();
			}
			try {
				share.setTurnover(NumberFormat.getNumberInstance(Locale.US).parse(tds.get(9).text()).doubleValue());
			} catch (ParseException e) {
				throw new NumberFormatException();
			}
			shares.add(share);
		}
		return shares;
	}
}
