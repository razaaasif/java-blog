import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { PostModel } from '../shared/model/post.model';
import { UserLoginService } from '../shared/services/auth/user-login.service';
import { PostService } from '../shared/services/post.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css'],
})
export class AddPostComponent implements OnInit {
  public addPost: FormGroup;
  private post: PostModel = new PostModel();
  constructor(
    private formService: FormBuilder,
    private postService: PostService,
    private userService: UserLoginService,
    private router: Router
  ) {
    this.addPost = this.formService.group({
      title: ['', Validators.required],
      content: ['', Validators.required],
      username: [''],
    });
  }

  ngOnInit(): void {}

  savePost(): void {
    console.log(this.addPost.value);
    this.post.content = this.addPost.get('content')?.value;
    this.post.title = this.addPost.get('title')?.value;
    this.post.username = this.userService.getCurrentUser();
    this.postService.savePost(this.post).subscribe(
      (data) => {
        console.log(JSON.stringify(data));
        this.router.navigateByUrl('/home');
      },
      (error) => {
        console.log(error);
        throw error;
      }
    );
  }
}
