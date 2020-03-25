<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="detail" class="col-md-9" style="display: none">
	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">警情正文</h3>
			<div class="box-tools pull-right">
				<button id="hideDetail"  type="button" class="btn btn-default btn-sm">
					<i class="fa fa-close"></i>
				</button>
			</div>
			<!-- /.box-header -->
			<div class="box-body no-padding">
				<div class="mailbox-read-info" id="title"></div>
				<!-- /.mailbox-read-info -->
				<div class="mailbox-controls with-border text-center">
					<div class="btn-group">
						<button type="button" class="btn btn-default btn-sm" data-toggle="tooltip" data-container="body" title="Delete">
							<i class="fa fa-trash-o"></i>
						</button>
						<button type="button" class="btn btn-default btn-sm" data-toggle="tooltip" data-container="body" title="Reply">
							<i class="fa fa-reply"></i>
						</button>
						<button type="button" class="btn btn-default btn-sm" data-toggle="tooltip" data-container="body" title="Forward">
							<i class="fa fa-share"></i>
						</button>
					</div>
					<!-- /.btn-group -->
					<button type="button" class="btn btn-default btn-sm" data-toggle="tooltip" title="" data-original-title="Print">
						<i class="fa fa-print"></i>
					</button>
				</div>
				<!-- /.mailbox-controls -->
				<div class="mailbox-read-message" id="content"></div>
				<!-- /.mailbox-read-message -->
			</div>
			<!-- /.box-body -->
			<div class="box-footer">
				<ul class="mailbox-attachments clearfix">
					<li>
						<span class="mailbox-attachment-icon"><i class="fa fa-file-pdf-o"></i></span>
						<div class="mailbox-attachment-info">
							<a href="javascript:;" class="mailbox-attachment-name">
								<i class="fa fa-paperclip"></i> 警情扫描件.pdf
							</a> 
							<span class="mailbox-attachment-size"> 1,245 KB 
								<a href="javascript:;" class="btn btn-default btn-xs pull-right">
									<i class="fa fa-cloud-download"></i>
								</a>
							</span>
						</div>
					</li>
				</ul>
			</div>
			<!-- /.box-footer -->
			<div class="box-footer">
				<div class="pull-right">
					<button id="preLetter" type="button" class="btn btn-default">
						<i class="fa fa-reply"></i> 上一封
					</button>
					<button id="nextLetter" type="button" class="btn btn-default">
						<i class="fa fa-share"></i> 下一封
					</button>
				</div>
				<button type="button" class="btn btn-default">
					<i class="fa fa-print"></i> 打印
				</button>
			</div>
			<div class="col-md-12">
				<h3>多证据指向</h3>
				<div id="let" style="border: 0px; height: 760px; width: 100%;"></div>
			</div>
			<!-- /.box-footer -->
		</div>
		<!-- /. box -->
	</div>
</div>