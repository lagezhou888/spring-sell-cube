package com.cgz.attachment.service.impl;

import com.cgz.attachment.model.Attachment;
import com.cgz.attachment.mapper.AttachmentMapper;
import com.cgz.attachment.service.IAttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Override
	public Attachment upload(Attachment attachment) {
		attachmentMapper.insert(attachment);
		return attachment;
	}

	@Override
	public String getByUserId(Integer userId) {
		String url = "";
		List<Attachment> list = attachmentMapper.selectByUserId(userId);
		if(list.size()>0) {
			url = list.get(0).getUrl();
		}
		return url;
	}

	@Override
	public Boolean deleteByUserId(Integer userId) {
	
		return attachmentMapper.deleteByUserId(userId);
	}

}
