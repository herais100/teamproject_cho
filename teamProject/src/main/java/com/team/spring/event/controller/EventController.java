package com.team.spring.event.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team.spring.event.dto.EventDto;
import com.team.spring.event.service.EventService;

@Controller
public class EventController {
	@Autowired
	private EventService service;
	
	@RequestMapping("/event/list")
	public ModelAndView getList(HttpServletRequest request) {
		service.getList(request);
		return new ModelAndView("event/list");
	}
	@RequestMapping("event/insertform")
	public ModelAndView insertform(HttpServletRequest request) {
		return new ModelAndView("event/insertform");
	}
	@RequestMapping("event/insert")
	public ModelAndView insert(@ModelAttribute EventDto dto, HttpServletRequest request) {
		String id="ragu";
		dto.setWriter(id);
		service.saveContent(dto);
		return new ModelAndView("redirect:/event/list.do");
	}
	@RequestMapping("/event/detail")
	public ModelAndView detail(HttpServletRequest request) {
		service.getDetail(request);
		return new ModelAndView("event/detail");
	}
	
	@RequestMapping("/event/delete")
	public ModelAndView authDelete(@RequestParam int num, HttpServletRequest request) {
		service.deleteContent(num);
		return new ModelAndView("redirect:/cafe/list.do");
	}
	@RequestMapping("/event/updateform")
	public ModelAndView authUpdateForm(ModelAndView mView, @RequestParam int num, 
			HttpServletRequest request) {
		service.getUpdateData(mView, num);
		mView.setViewName("event/updateform");
		return mView;
	}
	@RequestMapping("/event/update")
	public ModelAndView authUpdate(@ModelAttribute EventDto dto,HttpServletRequest request) {
		//서비스를 이용해서 글을 수정반영하고
		service.updateContent(dto);
		//dto 에 담긴 글 번호를 이용해서 글자세히 보기로 리다일렉트 이동시킨다.
		return new ModelAndView("redirect:/event/detail.do?num="+dto.getNum());
	}
}
