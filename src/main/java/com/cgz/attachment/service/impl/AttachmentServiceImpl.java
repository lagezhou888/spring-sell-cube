package com.cgz.attachment.service.impl;

import com.cgz.attachment.model.Attachment;
import com.cgz.attachment.mapper.AttachmentMapper;
import com.cgz.attachment.service.IAttachmentService;
import com.cgz.user.model.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caogzh
 * @since 2020-11-16
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {

	private AttachmentMapper attachmentMapper;
	
	@Override
	public Attachment upload(Attachment attachment) {
		attachmentMapper.insert(attachment);
		return attachment;
	}

}
