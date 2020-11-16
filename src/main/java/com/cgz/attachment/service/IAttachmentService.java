package com.cgz.attachment.service;

import com.cgz.attachment.model.Attachment;
import com.cgz.user.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caogzh
 * @since 2020-11-16
 */
public interface IAttachmentService extends IService<Attachment> {

	public Attachment upload(Attachment attachment);

}
