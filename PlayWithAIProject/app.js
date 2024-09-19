// JavaScript code for the to-do list application

// Select the elements from the DOM
const taskInput = document.getElementById('new-task-input');
const addTaskButton = document.getElementById('add-task-button');
const taskList = document.getElementById('task-list');

// Retrieve tasks from localStorage if available
document.addEventListener('DOMContentLoaded', loadTasksFromLocalStorage);

// Event listener for adding tasks
addTaskButton.addEventListener('click', addTask);

// Add a new task to the list
function addTask() {
  const taskText = taskInput.value.trim();
  if (taskText === '') {
    alert('Please enter a task');
    return;
  }

  const taskItem = createTaskElement(taskText);
  taskList.appendChild(taskItem);

  // Save task to localStorage
  saveTaskToLocalStorage(taskText);

  // Clear the input field after adding
  taskInput.value = '';
}

// Create a task element
function createTaskElement(taskText) {
  const li = document.createElement('li');
  li.className = 'task-item';
  li.textContent = taskText;

  const deleteButton = document.createElement('button');
  deleteButton.textContent = 'Delete';
  deleteButton.addEventListener('click', removeTask);

  li.appendChild(deleteButton);

  return li;
}

// Remove a task from the list and localStorage
function removeTask(event) {
  const taskItem = event.target.parentElement;
  const taskText = taskItem.textContent.replace('Delete', '').trim();

  // Remove task from the DOM
  taskItem.remove();

  // Remove task from localStorage
  removeTaskFromLocalStorage(taskText);
}

// Save task to localStorage
function saveTaskToLocalStorage(taskText) {
  let tasks = localStorage.getItem('tasks')
    ? JSON.parse(localStorage.getItem('tasks'))
    : [];

  tasks.push(taskText);
  localStorage.setItem('tasks', JSON.stringify(tasks));
}

// Remove task from localStorage
function removeTaskFromLocalStorage(taskText) {
  let tasks = JSON.parse(localStorage.getItem('tasks'));

  tasks = tasks.filter(task => task !== taskText);
  localStorage.setItem('tasks', JSON.stringify(tasks));
}

// Load tasks from localStorage on page load
function loadTasksFromLocalStorage() {
  let tasks = localStorage.getItem('tasks')
    ? JSON.parse(localStorage.getItem('tasks'))
    : [];

  tasks.forEach(task => {
    const taskItem = createTaskElement(task);
    taskList.appendChild(taskItem);
  });
}
