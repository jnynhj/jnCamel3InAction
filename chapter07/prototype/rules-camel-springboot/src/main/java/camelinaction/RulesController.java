package camelinaction;

import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * JAX-RS controller where we define our REST services
 */
@RestController
@RequestMapping("/api")
public class RulesController {

    // inject Camel template to call the Camel route from java code
    @EndpointInject("direct:inventory")
    private FluentProducerTemplate producer;

    @RequestMapping(method = RequestMethod.GET, value = "/rules/{cartIds}", produces = "application/json")
    public List<ItemDto> rules(@PathVariable("cartIds") String cartIds) {
        List<ItemDto> answer = new ArrayList<>();

        // find all items in inventory (use Camel to call legacy system)
        ItemsDto inventory = producer.request(ItemsDto.class);

        // filter out what we already have in the shopping cart
        for (ItemDto item : inventory.getItems()) {
            boolean duplicate = cartIds != null && cartIds.contains("" + item.getItemNo());
            if (!duplicate) {
                answer.add(item);
            }
        }

        // sort the list based on the ones we have the most of
        Collections.sort(answer, new ItemSorter());
        return answer;
	}

    /**
     * Used for sorting the items to recommend.
     */
	private static class ItemSorter implements Comparator<ItemDto> {

        @Override
        public int compare(ItemDto o1, ItemDto o2) {
            Integer n1 = o1.getNumber();
            Integer n2 = o2.getNumber();
            // we want the items with the highest number in our inventory first so -1
            return n1.compareTo(n2) * -1;
        }
    }
}