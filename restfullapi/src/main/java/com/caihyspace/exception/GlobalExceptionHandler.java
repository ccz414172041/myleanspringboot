package com.caihyspace.exception;

import com.caihyspace.exception.entity.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView view = new ModelAndView();
        view.addObject("exception", e);
        view.addObject("url", req.getRequestURL());
        view.setViewName(DEFAULT_ERROR_VIEW);
        return view;
    }

    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandle(HttpServletRequest request, CustomException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("SOME DATA");
        r.setUrl(request.getRequestURL().toString());
        return r;
    }


}
