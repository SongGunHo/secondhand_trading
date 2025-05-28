package org.koreait.member.exceptios;

import org.koreait.global.exceoption.NotFoundException;

public class MemberNotFoundException extends NotFoundException {
    public MemberNotFoundException(){
        super("NotFound.member");
        setErrorCode(true);

    }
}
