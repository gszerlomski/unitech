package biz.unitech.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class StorageController {
	

	/*@RequestMapping(value = "newProduct.htm", method = RequestMethod.POST)
	public ModelAndView addNewProduct(Model model, @Valid @ModelAttribute("product") Product product, BindingResult result)
			throws ServletException, IOException {

		if(result.hasErrors()) {
			return new ModelAndView("jsp/newProduct.jsp");
		}
		StorageService service = new StorageService();
		
		try {
			service.addProduct(product);
		} catch (DuplicateEntryException e) {
			ControllerUtils.addError(model, "Produkt o nazwie " + product.getName() + " już istnieje. Wybierz inną nazwę.");
			return new ModelAndView("jsp/newProduct.jsp");
		}
		model.addAttribute("product", new Product());
		ControllerUtils.addMessage(model, "Produkt " + product.getName() + " dodany.");
		return new ModelAndView("jsp/newProduct.jsp");
	}

	@RequestMapping(value = "productList.htm", method = RequestMethod.GET)
	public ModelAndView listProducts(Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		model.addAttribute("products", getAllProducts());

		return new ModelAndView("jsp/productList.jsp");
	}

	@RequestMapping(value = "newProduct.htm", method = RequestMethod.GET)
	public ModelAndView getNewProductPage(Model model) throws ServletException,
			IOException {

		model.addAttribute("product", new Product());
		return new ModelAndView("jsp/newProduct.jsp");
	}

	@RequestMapping(value = "addToStorage.htm", method = RequestMethod.POST)
	public ModelAndView addToStorage(Model model, HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("products") Products products)
			throws ServletException, IOException {

		StorageService service = new StorageService();

		service.updateProducts(products);
		model.addAttribute("message", new Message(
				"Zaktualizowano stany magazynowe"));

		return new ModelAndView("jsp/storageList.jsp");
	}

	@RequestMapping(value = "storageList.htm", method = RequestMethod.GET)
	public ModelAndView listStorage(Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		return new ModelAndView("jsp/storageList.jsp");
	}

	@RequestMapping(value = "addToStorage.htm", method = RequestMethod.GET)
	public ModelAndView getAddStoragePage(Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return new ModelAndView("jsp/addStorage.jsp");
	}

	@ModelAttribute("products")
	public Products getAllProducts() {
		StorageService service = new StorageService();
		return service.getAllProducts();
	}

	@ModelAttribute("currencies")
	public Map<String, String> getCurrencies() {

		Map<String, String> map = new HashMap<String, String>();

		for (Currency c : Currency.values()) {
			map.put(c.name(), c.name());
		}
		return map;
	}

	@ModelAttribute("standards")
	public Map<String, String> getStandards() {
		Map<String, String> map = new HashMap<String, String>();

		map.put("true", "true");
		map.put("false", "false");
		return map;
	}*/
}
