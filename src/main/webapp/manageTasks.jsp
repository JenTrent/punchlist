<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp" />

<body>

	<section id="section" class="vh-100" style="background-color: #eee;">
		<div class="container py-5 h-100">
			<div
				class="d-flex justify-content-center align-items-center h-100">
				<div class="col-sm-4 col-md-6 col-lg-8">

					<div class="card table-responsive">
						<div class="d-flex card-header p-3, form-inline">
							&emsp;&emsp;&emsp;&emsp;&emsp;<h2 >Punchlist</h2>
							
							<div class="puil-right">
							<section class="justify-content-right d-flex">
								<form
									action="<%=request.getContextPath()%>/manageAccount.jsp">
									<button class="me-2 btn btn-link far fa-user"
										data-mdb-toggle="tooltip" title="Manage Account"></button>
								</form>
								<form method="post"
									action="<%=request.getContextPath()%>/AccountController">
									<input type="hidden" name="cmd" value="attemptLogout">
									<button type="submit"
										class="me-2 btn btn-link fas fa-sign-out-alt"
										data-mdb-toggle="tooltip" title="Log Out"></button>
								</form>
							</section>
							</div>
						</div>
						<div class="card-body" data-mdb-perfect-scrollbar="true"
							style="position: relative; height: 400px">

							<c:if test="${requestScope.error != null}">
								<div id="alert" class="alert alert-danger" role="alert">
									<c:out value="${requestScope.error}"></c:out>
								</div>
							</c:if>

							<div class="row justify-content-center">
								<div class="col-auto col-lg-10">
								
									<table class="table mb-0 table-responsive">
										<thead>
											<form method="post"
												action="<%=request.getContextPath()%>/TaskController">
												<input type="hidden" name="cmd" value="createTask" />

												<tr class="fw-normal">
													<td class="text-justify"><input name="text"
														type="text" placeholder="Input Task"></td>
													<td>
														<h6 class="mb-0">
															<select name='priority' selected="${task.priority}">
																<option class="badge bg-danger,mb-0" value="1"
																	${task.priority == '1' ? 'selected' : ''}>High</option>
																<option class="badge bg-warning text-dark" value="2"
																	${task.priority == '2' ? 'selected' : ''}>Medium</option>
																<option class="badge bg-success" value="3"
																	${task.priority == '3' ? 'selected' : ''}>Low</option>
															</select>
														</h6>
													</td>
													<td><input type="submit" class="btn btn-primary"
														value="Add Task"></td>
												</tr>
											</form>
											<tr>
												<th scope="col" class="text-justify">Task</th>
												<th scope="col">Priority</th>
												<th scope="col">Actions</th>
											</tr>
										</thead>


										<c:forEach items="${requestScope.tasks}" var="task">

											<c:if test="${requestScope.editedTaskID != task.idString}">

												<tbody>
												<col style="width: 40%">
												<col style="width: 30%">
												<col style="width: 30%">
												<form method="post"
													action="<%=request.getContextPath()%>/TaskController">
													<input type="hidden" name="cmd" value="" /> <input
														type="hidden" name="taskId" value="${task.taskId}" /> <input
														type="hidden" name="completed" value="${task.completed}" />

													<tr class="fw-normal">
														<c:choose>
															<c:when test="${task.completed == 'true'}">
																<td class="text-justify"><s><c:out
																			value="${task.text}" /></s></td>
																<td class="align-middle"><s><c:out
																			value="${task.priorityString}"></c:out></s></td>
																<td>
																	<button type=submit value="ToggleCompleted"
																		onClick='submitForm(this, this.form)'
																		class="fas fa-undo-alt btn btn-secondary"
																		data-mdb-toggle="tooltip" title="Undo"></button>
																</td>
															</c:when>

															<c:otherwise>
																<td class="text-justify"><c:out
																		value="${task.text}"></c:out></td>
																<td class="align-middle"><c:choose>
																		<c:when test="${task.priorityString == 'High'}">
																			<h6 class="mb-0">
																				<span class="badge bg-danger"
																					style="font-size: 16px; color: white;"><c:out
																						value="${task.priorityString}"></c:out></span>
																			</h6>
																		</c:when>
																		<c:when test="${task.priorityString == 'Medium'}">
																			<h6 class="mb-0">
																				<span class="badge bg-warning"
																					style="font-size: 16px; color: white;"><c:out
																						value="${task.priorityString}"></c:out></span>
																			</h6>
																		</c:when>
																		<c:when test="${task.priorityString == 'Low'}">
																			<h6 class="mb-0">
																				<span class="badge bg-success"
																					style="font-size: 16px; color: white;"><c:out
																						value="${task.priorityString}"></c:out></span>
																			</h6>
																		</c:when>
																	</c:choose></td>
																<td>
																	<button type=submit value="Edit"
																		onClick='submitForm(this, this.form)'
																		class="fas fa-pencil-alt btn btn-primary"
																		data-mdb-toggle="tooltip" title="Edit"></button>
																	<button type=submit value="ToggleCompleted"
																		onClick='submitForm(this, this.form)'
																		class="fas fa-check btn btn-success"
																		data-mdb-toggle="tooltip" title="Complete"></button>
																	<button type="submit" value="Delete"
																		onClick='submitForm(this, this.form)'
																		class="fas fa-trash-alt btn btn-danger"
																		data-mdb-toggle="tooltip" title="delete"></button>
																</td>
															</c:otherwise>
														</c:choose>
													</tr>
												</form>
											</c:if>

											<c:if test="${requestScope.editedTaskID == task.idString}">

												<form method="post"
													action="<%=request.getContextPath()%>/TaskController">
													<input type="hidden" name="cmd" value="" /> <input
														type="hidden" name="taskId" value="${task.taskId}" /> <input
														type="hidden" name="completed" value="${task.completed}" />

													<tr class="fw-normal">


														<th><input name="text" type="text"
															value="<c:out value="${task.text}"></c:out>"></></th>
														<td class="align-middle"><select name='priority'
															selected="${task.priority}">
																<option value="1"
																	${task.priority == '1' ? 'selected' : ''}>High</option>
																<option value="2"
																	${task.priority == '2' ? 'selected' : ''}>Medium</option>
																<option value="3"
																	${task.priority == '3' ? 'selected' : ''}>Low</option>
														</select></td>
														<td>
															<button type=submit value="Update"
																onClick='submitForm(this, this.form)'
																class="fas fa-clipboard-check btn btn-info"
																data-mdb-toggle="tooltip" title="Update"></button>
															<button type=submit value="ToggleCompleted"
																onClick='submitForm(this, this.form)'
																class="fas fa-check btn btn-success"
																data-mdb-toggle="tooltip" title="Complete"></button>
															<button type="submit" value="Delete"
																onClick='submitForm(this, this.form)'
																class="fas fa-trash-alt btn btn-danger"
																data-mdb-toggle="tooltip" title="delete"></button>
														</td>
													</tr>
												</form>
											</c:if>
										</c:forEach>
										</tbody>
									</table>
								</div>
							</div>

						</div>
					</div>
				</div>
	</section>
	<script type="text/javascript">
		function submitForm(button, form) {
			if (button.value == 'Update') {
				form.cmd.value = 'updateTask';
			}
			if (button.value == 'Delete') {
				form.cmd.value = 'deleteTask';
			}
			if (button.value == 'Edit') {
				form.cmd.value = 'editTask';
			}
			if (button.value == "ToggleCompleted") {
				form.cmd.value = 'toggleCompleted';
			}
			form.submit();
		}

		function openPage(pageUrl) {
			window.location.href = pageURL;
		}
	</script>

</body>

<jsp:include page="footer.jsp" />