package com.example.balticnasdaqstocks.service;

import com.example.balticnasdaqstocks.Model.Stock;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


@Service
public class NasdaqService {
	public List<Stock> webClient() {
		WebClient webClient = WebClient.create("https://nasdaqbaltic.com/statistics/en/shares?date=2023-11-08");
		Mono<String> response = webClient.get().retrieve().bodyToMono(String.class);

		Document doc = Jsoup.parse(response.block(), "UTF-8");

		List<Stock> stocks = new ArrayList<>();

		Element content = doc.getElementById("shares_form");
		Elements rows = content.getElementsByTag("tr");

		for (var row : rows) {
			Element td = row.select("td").first();
			if ((td == null) || (td.attr("data-sort-by").length() == 0)) {
				continue;
			}
			Elements tds = row.getElementsByTag("td");
			Stock stock = new Stock();
			stock.setCompany(tds.get(0).select("a").text());
			stock.setTicker(tds.get(1).select("a").text());
			if (tds.get(2).text().trim().length() > 0 && !Objects.equals(tds.get(6).text(), "-")) {
				stock.setLastPrice(Double.valueOf(tds.get(2).text()));
			}
			if (tds.get(3).text().trim().length() > 0 && !Objects.equals(tds.get(6).text(), "-")) {
				stock.setChange(Double.valueOf(tds.get(3).text()));
			}
			if (tds.get(4).text().trim().length() > 0 && !Objects.equals(tds.get(6).text(), "-")) {
				stock.setPercentage(Double.valueOf(tds.get(4).text()));
			}
			if (tds.get(5).text().trim().length() > 0 && !Objects.equals(tds.get(6).text(), "-")) {
				stock.setBid(Double.valueOf(tds.get(5).text()));
			}
			if (tds.get(6).text().trim().length() > 0 && !Objects.equals(tds.get(6).text(), "-")) {
				stock.setAsk(Double.valueOf(tds.get(6).text()));
			}
			try {
				stock.setTrades(NumberFormat.getNumberInstance(Locale.US).parse(tds.get(7).text()).intValue());
			} catch (ParseException e) {
				throw new NumberFormatException();
			}
			try {
				stock.setVolume(NumberFormat.getNumberInstance(Locale.US).parse(tds.get(8).text()).intValue());
			} catch (ParseException e) {
				throw new NumberFormatException();
			}
			try {
				stock.setTurnover(NumberFormat.getNumberInstance(Locale.US).parse(tds.get(9).text()).doubleValue());
			} catch (ParseException e) {
				throw new NumberFormatException();
			}
			stocks.add(stock);
		}
		return stocks;
	}
}
