package com.bookstore.controller;

import com.bookstore.domain.Book;
import com.bookstore.domain.CartItem;
import com.bookstore.domain.ShoppingCart;
import com.bookstore.domain.User;
import com.bookstore.service.api.UserService;
import com.bookstore.service.api.CartItemService ;
import com.bookstore.service.api.ShoppingCartService ;
import com.bookstore.service.api.BookService ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    UserService userService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    BookService bookService;
    @Autowired
    ShoppingCartService shoppingCartService;
    @RequestMapping("/cart")
    public String shoppingCart(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        shoppingCartService.updateShoppingCart(shoppingCart);

        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", shoppingCart);

        return "shoppingCart";
    }


    @RequestMapping("/addItem")
    public String addItem(
            @ModelAttribute("book") Book book,
            @ModelAttribute("qty") String qty,
            Model model, Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());
        book = bookService.findOne(book.getId());

        if (Integer.parseInt(qty) > book.getInStockNumber()) {
            model.addAttribute("notEnoughStock", true);
            return "forward:/bookDetail?id="+book.getId();
        }

        CartItem cartItem = cartItemService.addBookToCartItem(book, user, Integer.parseInt(qty));
        model.addAttribute("addBookSuccess", true);

        return "forward:/bookDetail?id="+book.getId();
    }


    @RequestMapping("/updateCartItem")
    public String updateShoppingCartItem( @ModelAttribute("id")Long cartItemId,
                                      @ModelAttribute("qty") int qty ){

            CartItem cartItem = cartItemService.findById(cartItemId);
            cartItem.setQty(qty);
            cartItemService.updateCartItem(cartItem);
        return "forward:/shoppingCart/cart";
    }
    @RequestMapping("/removeItem")
    public String updateShoppingCartItem( @ModelAttribute("id")Long id){
          cartItemService.removeCartItem(cartItemService.findById(id));
         return "forward:/shoppingCart/cart";
    }
}
