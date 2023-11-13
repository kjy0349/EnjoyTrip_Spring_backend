package com.tripinfo.member.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tripinfo.board.controller.BoardRestController;
import com.tripinfo.board.service.BoardServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class MemberRestController {

}
